package com.enseirb.timtim.map_eirb.business;

import com.enseirb.timtim.map_eirb.business.listener.IPOICollectionBusinessListener;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public interface IPOICollectionBusiness {


    POICollection retrievePOICollection(POIType poiType, IPOICollectionBusinessListener listener);

}
