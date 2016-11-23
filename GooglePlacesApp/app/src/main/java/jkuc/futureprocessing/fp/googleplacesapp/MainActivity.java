package jkuc.futureprocessing.fp.googleplacesapp;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.map.presenter.MapPresenter;

public class MainActivity extends AppCompatActivity {

    private MapPresenter presenter = new MapPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        presenter.setView(bottomNavigationView, getSupportFragmentManager());
    }
}
