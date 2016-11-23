package jkuc.futureprocessing.fp.googleplacesapp.data.retrofit;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import rx.Observable;

public class PlacesDownloader implements IPlacesDownloader {

    public final PlacesService service;

    public PlacesDownloader(PlacesService service) {
        this.service = service;
    }

    @Override
    public Observable<Result> getNearbyBars(String location,
                                            int radius,
                                            String type,
                                            String key)

    {
        return service.getNearbyBars(location, radius, type, key);
    }
}
