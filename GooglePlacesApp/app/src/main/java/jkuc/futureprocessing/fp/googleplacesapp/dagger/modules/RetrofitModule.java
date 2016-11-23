package jkuc.futureprocessing.fp.googleplacesapp.dagger.modules;


import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.data.retrofit.PlacesService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import timber.log.Timber;

@Module
public class RetrofitModule {

    @Provides
    public PlacesService providesPlacesService(Retrofit retrofit){
        return retrofit.create(PlacesService.class);
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

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.valueOf(BuildConfig.END_POINT_LOG_LEVEL));

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addNetworkInterceptor(loggingInterceptor);

        Moshi moshi = new Moshi.Builder().build();

        return new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .baseUrl(BuildConfig.END_POINT_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();


    }
}
