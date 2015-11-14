package com.enseirb.timtim.map_eirb.dto;


public class InternetAccessDTO extends DataDTO {
    public String name;
    public String accessType;
    public String paid;

    public InternetAccessDTO(String longitude, String latitude, String name, String accessType, String paid) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
        this.accessType = accessType;
        this.paid = paid;
    }

}
