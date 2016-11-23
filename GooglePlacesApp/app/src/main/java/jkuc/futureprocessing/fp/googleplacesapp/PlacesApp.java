package jkuc.futureprocessing.fp.googleplacesapp;

import android.app.Application;

import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import timber.log.Timber;

public class PlacesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppComponent.initComponent(this);
        Timber.plant(new Timber.DebugTree());
    }
}
