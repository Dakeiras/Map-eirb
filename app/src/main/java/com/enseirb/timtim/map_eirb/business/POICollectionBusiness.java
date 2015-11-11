package com.enseirb.timtim.map_eirb.business;

import com.enseirb.timtim.map_eirb.business.listener.IPOICollectionBusinessListener;
import com.enseirb.timtim.map_eirb.converter.IPOICollectionConverter;
import com.enseirb.timtim.map_eirb.converter.listener.IPOICollectionConverterListener;
import com.enseirb.timtim.map_eirb.converter.POICollectionConverter;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public class POICollectionBusiness implements IPOICollectionBusiness {

    private IPOICollectionConverter poiCollectionConverter;

    public POICollectionBusiness(){
        poiCollectionConverter = new POICollectionConverter();
    }

    @Override
    public POICollection retrievePOICollection(POIType poiType, final IPOICollectionBusinessListener listener) {
        poiCollectionConverter.retrievePOICollection(poiType, new IPOICollectionConverterListener() {

            @Override
            public void onSuccess(POICollection poiCollection) {
                listener.onSuccess(poiCollection);
            }

            @Override
            public void onError(String message) {
                listener.onError(message);
            }

        });

        return null;
    }
}
