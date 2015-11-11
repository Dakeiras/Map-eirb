package com.enseirb.timtim.map_eirb.dao.listener;

import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;

public interface IPOICollectionDAOListener {
    //TODO:
    void onSuccess(POICollectionDTO poiCollection);
    void onError(String message);
}
