package com.enseirb.timtim.map_eirb.model;

public class ToiletPOI  extends POI  {
    public String type;

    public ToiletPOI(double longitude, double latitude, String type) {
        super(longitude, latitude);
        this.type = type;
    }

    @Override
    public String getTitle() {
        return type;
    }
}
