package jkuc.futureprocessing.fp.googleplacesapp.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.dagger.AppComponent;
import jkuc.futureprocessing.fp.googleplacesapp.data.GooglePlacesDataProvider;
import jkuc.futureprocessing.fp.googleplacesapp.list.data.PlacesConverter;
import jkuc.futureprocessing.fp.googleplacesapp.list.presenter.PlacesAdapter;

public class ListFragment extends Fragment {
    @Inject
    protected GooglePlacesDataProvider provider;

    private PlacesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent.instance.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        adapter = new PlacesAdapter(getActivity());
        adapter.addItems(PlacesConverter.from(provider.getLastResult()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return v;
    }
}
