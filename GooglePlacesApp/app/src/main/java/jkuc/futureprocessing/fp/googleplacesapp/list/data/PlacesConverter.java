package jkuc.futureprocessing.fp.googleplacesapp.list.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jkuc.futureprocessing.fp.googleplacesapp.data.entity.PlaceDescription;
import jkuc.futureprocessing.fp.googleplacesapp.data.entity.Result;

public class PlacesConverter {

    public static List<Place> from(Result result) {
        if(result != null) {
            List<PlaceDescription> placeDescriptionsResponse = result.results;
            if(placeDescriptionsResponse != null) {
                List<Place> places = new ArrayList<>(placeDescriptionsResponse.size());
                for(PlaceDescription placeDescription : placeDescriptionsResponse) {
                    Place place = new Place(placeDescription.name,
                                            placeDescription.photos != null && placeDescription.photos.get(0) != null ? placeDescription.photos.get(0).photo_reference : null);
                    places.add(place);
                }
                return places;
            }
        }

        return Collections.emptyList();
    }
}
