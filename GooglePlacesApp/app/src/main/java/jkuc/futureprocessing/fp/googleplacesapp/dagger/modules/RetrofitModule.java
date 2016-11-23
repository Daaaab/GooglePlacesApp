package jkuc.futureprocessing.fp.googleplacesapp.dagger.modules;


import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.data.GooglePlacesDataProvider;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.IPlacesDownloader;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.MockDownloader;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.PlacesService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public GooglePlacesDataProvider providerGooglePlacesDataProvider(IPlacesDownloader service){
        return new GooglePlacesDataProvider(service);
    }

    @Provides
    public IPlacesDownloader providesPlacesService(Retrofit retrofit){
        return new MockDownloader();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.tag("RetroLogger").d(message);
            }
        });

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(loggingInterceptor);

        Moshi moshi = new Moshi.Builder().build();

        return new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(BuildConfig.END_POINT_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();


    }
}
