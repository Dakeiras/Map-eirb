package com.enseirb.timtim.map_eirb.dao;

import android.os.AsyncTask;

import com.enseirb.timtim.map_eirb.client.IPOICollectionClient;
import com.enseirb.timtim.map_eirb.client.POICollectionClient;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.parser.IPOICollectionParser;
import com.enseirb.timtim.map_eirb.parser.JSONInternetParser;

public class InternetCollectionDAO implements IPOICollectionDAO{
    public static final String URL = "http://odata.bordeaux.fr/v1/databordeaux/sigaccesinternet/?format=json";

    private IPOICollectionClient poiCollectionClient;
    private IPOICollectionParser poiCollectionParser;

    public InternetCollectionDAO(){
        poiCollectionClient = new POICollectionClient();
        poiCollectionParser = new JSONInternetParser();
    }

    @Override
    public void retrievePOICollection(final IPOICollectionDAOListener listener) {
        new AsyncTask<String, Integer, POICollectionDTO>() {
            @Override
            protected POICollectionDTO doInBackground(String... params) {
                POICollectionDTO internetCollectionDTO;
                String jsonInternet = poiCollectionClient.retrievePOICollection(params[0]);
                internetCollectionDTO = poiCollectionParser.parse(jsonInternet);
                listener.onSuccess(internetCollectionDTO);
                return internetCollectionDTO;
            }
        }.execute(URL);
    }

}
