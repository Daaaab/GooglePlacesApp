package jkuc.futureprocessing.fp.googleplacesapp.map.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Inject;

import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import jkuc.futureprocessing.fp.googleplacesapp.data.GooglePlacesDataProvider;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.PlaceDescription;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MapFragment extends Fragment {

    private MapView mapView;
    private GoogleMap googleMap;

    private final int LOCATION_REQUEST_CODE = 1;

    @Inject
    protected ReactiveLocationProvider locationProvider;

    @Inject
    protected GooglePlacesDataProvider provider;

    private Subscription locationSubscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent.instance.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = (MapView) v.findViewById(R.id.mapView);
        initMap(savedInstanceState);

        return v;
    }

    @SuppressWarnings({"MissingPermission"})
    private void getLastKnownLocation() {
        locationSubscription = locationProvider.getLastKnownLocation()
                                               .subscribeOn(Schedulers.io())
                                               .subscribe(new Action1<Location>() {
                                                   @Override
                                                   public void call(Location location) {
                                                       zoomOnMyLocation(location);
                                                       getNearbyPlaces(location);
                                                   }
                                               }, new Action1<Throwable>() {
                                                   @Override
                                                   public void call(Throwable throwable) {
                                                       Timber.d("Location error: " + throwable.getMessage());
                                                   }
                                               }, new Action0() {
                                                   @Override
                                                   public void call() {
                                                       Timber.d("Complete");
                                                   }
                                               });
    }

    private void getNearbyPlaces(Location location) {
        provider.getNearbyBars(location, new Action1<Result>() {
            @Override
            public void call(Result result) {
                for(PlaceDescription pd : result.results) {
                    if(pd.photos != null && !pd.photos.isEmpty()) {

                        showNewMarker(new LatLng(pd.geometry.location.lat, pd.geometry.location.lng), pd.photos.get(0).photo_reference);
                    } else {
                        showNewMarker(new LatLng(pd.geometry.location.lat, pd.geometry.location.lng), null);
                    }
                }
            }
        });
    }

    private void showNewMarker(final LatLng position, final String photoRef) {
        if(photoRef != null) {
            // Get async bar image from web
            // when loaded show on map
            Observable.create(new Observable.OnSubscribe<Bitmap>() {
                                  @Override
                                  public void call(Subscriber<? super Bitmap> subscriber) {
                                      subscriber.onNext(getImage(photoRef));
                                      subscriber.onCompleted();
                                  }
                              }
            ).subscribeOn(Schedulers.newThread())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Subscriber<Bitmap>() {
                          @Override
                          public void onNext(Bitmap bitmap) {

                              googleMap.addMarker(new MarkerOptions()
                                                          .position(position)
                                                          .icon(BitmapDescriptorFactory.fromBitmap(bitmap)));
                          }

                          @Override
                          public void onCompleted() {
                          }

                          @Override
                          public void onError(Throwable e) {
                          }
                      });
        } else {
            googleMap.addMarker(new MarkerOptions()
                                        .position(position));
        }
    }

    private Bitmap getImage(String photoRef) {
        Bitmap bmp = null;
        try {
            URL url = new URL(BuildConfig.PHOTO_URL + photoRef);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            //return rounded bitmap
            bmp = roundBitmap(BitmapFactory.decodeStream(is));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    private Bitmap roundBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                                            bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                                   bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        float radius = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() / 2 : bitmap.getWidth() / 2;

        canvas.drawCircle(bitmap.getWidth() / 2,
                          bitmap.getHeight() / 2, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    @SuppressWarnings({"MissingPermission"})
    private void setMyLocationOnMap() {
        googleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case LOCATION_REQUEST_CODE: {
                if(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastKnownLocation();
                    setMyLocationOnMap();
                } else {
                    //TODO
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(locationSubscription != null) {
            locationSubscription.unsubscribe();
        }
    }

    @SuppressWarnings({"MissingPermission"})
    private void zoomOnMyLocation(Location location) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

    private void initMap(@Nullable Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch(Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;

                // Check for permissions
                if(ContextCompat.checkSelfPermission(getActivity(),
                                                     Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                                                      new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                                      LOCATION_REQUEST_CODE);
                } else {
                    getLastKnownLocation();
                    setMyLocationOnMap();
                }
            }
        });
    }
}
