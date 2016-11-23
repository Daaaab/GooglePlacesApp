package jkuc.futureprocessing.fp.googleplacesapp.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.modules.MainModule;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.modules.RetrofitModule;
import jkuc.futureprocessing.fp.googleplacesapp.data.GooglePlacesDataProvider;
import jkuc.futureprocessing.fp.googleplacesapp.map.view.MapFragment;

@Singleton
@Component(modules = {MainModule.class, RetrofitModule.class})
public abstract class AppComponent {

    public static AppComponent instance;

    public static void initComponent(Context context){
        instance = DaggerAppComponent
                .builder()
                .mainModule(new MainModule(context))
                .build();
    }

    public abstract void inject(MapFragment mapFragment);

    public abstract void inject(GooglePlacesDataProvider googlePlacesDataProvider);
}
