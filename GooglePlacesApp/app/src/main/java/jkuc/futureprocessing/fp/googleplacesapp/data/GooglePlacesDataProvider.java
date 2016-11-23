package jkuc.futureprocessing.fp.googleplacesapp.data;

import android.location.Location;

import javax.inject.Inject;

import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.PlacesResponse;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.PlacesService;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=50.2772238,18.6850398&radius=150&type=bar&key=AIzaSyAflAwbkbNRQ4NdEtCKAfDjv_Q9kHOIWMg
//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=bar&key=AIzaSyCaq9ttTXRckcXMJLHwuY-QJxyePJZ6IRI
public class GooglePlacesDataProvider {

    @Inject
    protected PlacesService placesService;

    public void getNearbyBars(Location location) {

        String locationString = location.getLatitude() + "," + location.getLongitude();

        placesService.getNearbyBars(locationString, 10000, "bar", BuildConfig.PLACES_API_KEY)
                     .subscribeOn(Schedulers.io())
                     .subscribe(new Action1<PlacesResponse>() {
                         @Override
                         public void call(PlacesResponse placesResponse) {
                             Timber.d("");
                         }
                     }, new Action1<Throwable>() {
                         @Override
                         public void call(Throwable throwable) {
                             Timber.d("");
                         }
                     });
    }

    public GooglePlacesDataProvider() {
        AppComponent.instance.inject(this);
    }
}
