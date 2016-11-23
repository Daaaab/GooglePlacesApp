package jkuc.futureprocessing.fp.googleplacesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.list.view.ListFragment;
import jkuc.futureprocessing.fp.googleplacesapp.map.view.MapFragment;

public class MainActivityPresenter {

    public void setView(BottomNavigationView bottomMenu, FragmentManager fm) {
        setupNavigation(bottomMenu, fm);
        openMapFragment(fm);
    }

    private void setupNavigation(BottomNavigationView bottomMenu, final FragmentManager fm) {
        bottomMenu.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch(item.getItemId()) {
                            case R.id.action_map:
                                openMapFragment(fm);
                                break;
                            case R.id.action_list:
                                openListFragment(fm);
                                break;
                        }
                        return false;
                    }
                });
    }

    private void openMapFragment(FragmentManager fm){
        MapFragment mapFragment = new MapFragment();
        fm.beginTransaction()
          .replace(R.id.frame, mapFragment, mapFragment.getClass().getName())
          .commit();
    }

    private void openListFragment(FragmentManager fm){
        ListFragment listFragment = new ListFragment();
        fm.beginTransaction()
          .replace(R.id.frame, listFragment, listFragment.getClass().getName())
          .commit();
    }
}
