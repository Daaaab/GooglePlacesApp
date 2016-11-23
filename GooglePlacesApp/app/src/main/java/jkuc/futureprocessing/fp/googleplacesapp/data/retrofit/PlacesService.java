package jkuc.futureprocessing.fp.googleplacesapp.data.retrofit;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.PlaceDescription;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface PlacesService {

    @GET("nearbysearch/json")
    Observable<Result> getNearbyBars(@Query("location") String location,
                                     @Query("radius") int radius,
                                     @Query("type") String type,
                                     @Query("key") String key);
}
