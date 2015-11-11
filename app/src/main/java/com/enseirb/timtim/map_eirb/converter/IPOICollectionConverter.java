package com.enseirb.timtim.map_eirb.converter;

import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public interface IPOICollectionConverter {
    POICollection retrievePOICollection(POIType type, IPOICollectionConverterListener listener);
}

