package com.enseirb.timtim.map_eirb.dto;


public class ToiletsDTO extends DataDTO {
    public String type;

    public ToiletsDTO(String longitude, String latitude, String type) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }
}
