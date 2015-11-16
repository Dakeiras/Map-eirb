package com.enseirb.timtim.map_eirb.converter;

import com.enseirb.timtim.map_eirb.dto.POIDTO;
import com.enseirb.timtim.map_eirb.model.POI;

public interface IPOIsConverter {


    POI convertDTO(POIDTO collectionDTO);

}
