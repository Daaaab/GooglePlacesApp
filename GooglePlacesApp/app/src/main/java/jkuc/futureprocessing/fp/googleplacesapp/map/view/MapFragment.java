package jkuc.futureprocessing.fp.googleplacesapp.map.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import jkuc.futureprocessing.fp.googleplacesapp.data.GooglePlacesDataProvider;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import timber.log.Timber;

public class MapFragment extends Fragment {

    private MapView mapView;
    private GoogleMap googleMap;

    private final int LOCATION_REQUEST_CODE = 1;

    @Inject
    protected ReactiveLocationProvider locationProvider;

    private Subscription locationSubscription;


    GooglePlacesDataProvider provider = new GooglePlacesDataProvider();

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
                                               .subscribe(new Action1<Location>() {
                                                   @Override
                                                   public void call(Location location) {
                                                       zoomOnMyLocation(location);
                                                       provider.getNearbyBars(location);
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
//        if(locationSubscription != null){
//            locationSubscription.unsubscribe();
//        }

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
