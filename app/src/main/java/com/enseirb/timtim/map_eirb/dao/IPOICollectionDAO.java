package com.enseirb.timtim.map_eirb.dao;

import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;

public interface IPOICollectionDAO {
    void retrievePOICollection(final IPOICollectionDAOListener listener);
}
