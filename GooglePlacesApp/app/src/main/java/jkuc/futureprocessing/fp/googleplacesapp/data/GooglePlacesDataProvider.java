package jkuc.futureprocessing.fp.googleplacesapp.data;

import android.location.Location;

import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.IPlacesDownloader;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class GooglePlacesDataProvider {

    private final IPlacesDownloader placesService;

    private Result lastResult;

    public GooglePlacesDataProvider(IPlacesDownloader placesService){
        this.placesService = placesService;
        AppComponent.instance.inject(this);
    }

    public void getNearbyBars(Location location) {

        String locationString = location.getLatitude() + "," + location.getLongitude();

        placesService.getNearbyBars(locationString, 10000, "bar", BuildConfig.PLACES_API_KEY)
                     .subscribeOn(Schedulers.io())
                     .subscribe(new Action1<Result>() {
                         @Override
                         public void call(Result result) {
                             Timber.d("");
                             lastResult = result;
                         }
                     });
    }

    public Result getLastResult(){
        return lastResult;
    }
}
