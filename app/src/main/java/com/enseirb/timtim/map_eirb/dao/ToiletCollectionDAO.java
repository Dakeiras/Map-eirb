package com.enseirb.timtim.map_eirb.dao;

import android.os.AsyncTask;

import com.enseirb.timtim.map_eirb.client.IPOICollectionClient;
import com.enseirb.timtim.map_eirb.client.POICollectionClient;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.parser.IPOICollectionParser;
import com.enseirb.timtim.map_eirb.parser.JSONToiletParser;

public class ToiletCollectionDAO implements IPOICollectionDAO{
    public static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/sigsanitaire/?format=json";

    private IPOICollectionClient poiCollectionClient;
    private IPOICollectionParser poiCollectionParser;

    public ToiletCollectionDAO(){
        poiCollectionClient = new POICollectionClient();
        poiCollectionParser = new JSONToiletParser();
    }

    @Override
    public void retrievePOICollection(final IPOICollectionDAOListener listener) {
        new AsyncTask<String, Integer, POICollectionDTO>() {
            @Override
            protected POICollectionDTO doInBackground(String... params) {
                POICollectionDTO toiletCollectionDTO;
                String jsonToilet = poiCollectionClient.retrievePOICollection(params[0]);
                toiletCollectionDTO = poiCollectionParser.parse(jsonToilet);
                listener.onSuccess(toiletCollectionDTO);
                return toiletCollectionDTO;
            }
        }.execute(URL);
    }

}
