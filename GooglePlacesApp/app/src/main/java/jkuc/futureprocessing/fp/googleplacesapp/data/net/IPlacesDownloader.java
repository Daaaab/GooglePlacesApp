package jkuc.futureprocessing.fp.googleplacesapp.data.net;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import rx.Observable;

public interface IPlacesDownloader {

    Observable<Result> getNearbyBars(String location,
                                     int radius,
                                     String type,
                                     String key);
}
