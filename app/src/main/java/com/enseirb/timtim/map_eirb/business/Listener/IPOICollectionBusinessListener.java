package com.enseirb.timtim.map_eirb.business.listener;

import com.enseirb.timtim.map_eirb.model.POICollection;

public interface IPOICollectionBusinessListener {

    void onSuccess(POICollection poiCollection);
    void onError(String message);

}
