package jkuc.futureprocessing.fp.googleplacesapp.data.entity;

import java.util.List;

public class PlaceDescription {

    public final List<Photo> photos;
    public final Geometry geometry;
    public final String name;

    public PlaceDescription(List<Photo> photos, Geometry geometry, String name) {
        this.photos = photos;
        this.geometry = geometry;
        this.name = name;
    }
}
