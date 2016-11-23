package jkuc.futureprocessing.fp.googleplacesapp.dagger.modules;

import android.content.Context;
import android.location.LocationProvider;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.LocationSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;

@Module
public class MainModule {

    private final Context context;

    public MainModule(Context context){
        this.context = context;
    }

    @Singleton
    @Provides
    public ReactiveLocationProvider providesLocationProvider(){
        return new ReactiveLocationProvider(context);
    }

}
