package com.enseirb.timtim.map_eirb.converter.v2;

import com.enseirb.timtim.map_eirb.dto.POIDTO;
import com.enseirb.timtim.map_eirb.model.POI;

public interface IPOIConverter {


    POI convertDTO(POIDTO collectionDTO);

}
