package com.enseirb.timtim.map_eirb.dao;

import com.enseirb.timtim.map_eirb.dto.IPOICollectionDTO;

public interface IPOICollectionDAOListener {
    //TODO:
    void onSuccess(IPOICollectionDTO poiCollection);
    void onError(String message);
}
