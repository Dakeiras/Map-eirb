package com.enseirb.timtim.map_eirb.dto;


public class POIToiletsDTO extends POIDTO {
    public String type;

    public POIToiletsDTO(String longitude, String latitude, String type) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }
}
