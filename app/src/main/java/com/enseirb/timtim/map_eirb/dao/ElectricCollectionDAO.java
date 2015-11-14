package com.enseirb.timtim.map_eirb.dao;

import android.os.AsyncTask;

import com.enseirb.timtim.map_eirb.client.IPOICollectionClient;
import com.enseirb.timtim.map_eirb.client.POICollectionClient;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.parser.IPOICollectionParser;
import com.enseirb.timtim.map_eirb.parser.JSONElectricParser;

public class ElectricCollectionDAO implements IPOICollectionDAO{
    public static final String URL = "http://www.chargepulse.com/download/?type=json&client=MairieBx";

    private IPOICollectionClient poiCollectionClient;
    private IPOICollectionParser poiCollectionParser;

    public ElectricCollectionDAO(){
        poiCollectionClient = new POICollectionClient();
        poiCollectionParser = new JSONElectricParser();
    }

    @Override
    public void retrievePOICollection(final IPOICollectionDAOListener listener) {
        new AsyncTask<String, Integer, POICollectionDTO>() {
            @Override
            protected POICollectionDTO doInBackground(String... params) {
                POICollectionDTO electricCollectionDTO;
                String jsonElectric = poiCollectionClient.retrievePOICollection(params[0]);
                electricCollectionDTO = poiCollectionParser.parse(jsonElectric);
                listener.onSuccess(electricCollectionDTO);
                return electricCollectionDTO;
            }
        }.execute(URL);
    }

}
