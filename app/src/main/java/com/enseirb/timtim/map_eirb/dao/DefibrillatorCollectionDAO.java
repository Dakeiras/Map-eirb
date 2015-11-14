package com.enseirb.timtim.map_eirb.dao;

import android.os.AsyncTask;

import com.enseirb.timtim.map_eirb.client.IPOICollectionClient;
import com.enseirb.timtim.map_eirb.client.POICollectionClient;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.parser.IPOICollectionParser;
import com.enseirb.timtim.map_eirb.parser.JSONDefibrillatorParser;

public class DefibrillatorCollectionDAO implements IPOICollectionDAO{

    public static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/defibrillateurs/?format=json";

    private IPOICollectionClient poiCollectionClient;
    private IPOICollectionParser poiCollectionParser;

    public DefibrillatorCollectionDAO(){
        poiCollectionClient = new POICollectionClient();
        poiCollectionParser = new JSONDefibrillatorParser();
    }

    @Override
    public void retrievePOICollection(final IPOICollectionDAOListener listener) {
        new AsyncTask<String, Integer, POICollectionDTO>() {
            @Override
            protected POICollectionDTO doInBackground(String... params) {
                POICollectionDTO defibrillatorCollectionDTO;
                String jsonDefibrillator = poiCollectionClient.retrievePOICollection(params[0]);
                defibrillatorCollectionDTO = poiCollectionParser.parse(jsonDefibrillator);
                listener.onSuccess(defibrillatorCollectionDTO);
                return defibrillatorCollectionDTO;
            }
        }.execute(URL);
    }
}
