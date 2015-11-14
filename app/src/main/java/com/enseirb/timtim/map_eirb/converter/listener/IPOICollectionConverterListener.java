package com.enseirb.timtim.map_eirb.converter.listener;

import com.enseirb.timtim.map_eirb.model.POICollection;

public interface IPOICollectionConverterListener {

    void onSuccess(POICollection poiCollection);
    void onError(String message);

}
