package jkuc.futureprocessing.fp.googleplacesapp.data.retrofit;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import rx.Observable;

public interface IPlacesDownloader {

    Observable<Result> getNearbyBars(String location,
                                     int radius,
                                     String type,
                                     String key);
}
