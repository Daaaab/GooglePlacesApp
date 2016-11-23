package jkuc.futureprocessing.fp.googleplacesapp.list.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import jkuc.futureprocessing.fp.googleplacesapp.BuildConfig;
import jkuc.futureprocessing.fp.googleplacesapp.R;
import jkuc.futureprocessing.fp.googleplacesapp.list.data.Place;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final Context context;

    private List<Place> places = new ArrayList<>();

    public PlacesAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PlacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(PlacesAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(context, places.get(position));
    }

    public void addItems(List<Place> newItems) {
        places.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
        }

        public void bindData(Context context, Place place) {
            String tmp = BuildConfig.PHOTO_URL + place.imageURL + "&key=" + BuildConfig.PLACES_API_KEY;
            Glide.with(context)
                 .load(tmp)
                 .diskCacheStrategy(DiskCacheStrategy.RESULT)
                 .centerCrop()
                 .into(image);
            title.setText(place.title);
        }
    }
}