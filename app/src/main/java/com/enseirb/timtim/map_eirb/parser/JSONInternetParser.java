package com.enseirb.timtim.map_eirb.parser;

import com.enseirb.timtim.map_eirb.dto.POIInternetAccessDTO;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JSONInternetParser implements IPOICollectionParser {
    private static final String INTERNET_OBJECT ="d";
    private static final String INTERNET_NAME = "nom";
    private static final String INTERNET_NATURE = "type_acces";
    private static final String INTERNET_GRATUITY = "payant";
    private static final String LONGITUDE = "x_long";
    private static final String LATITUDE = "y_lat";
    @Override
    public POICollectionDTO parse(String jsonDefibrillator)  {
        POICollectionDTO poiInternetAccess = new POICollectionDTO();
        try {
            JSONArray internetAccessArray;
            JSONObject reader = null;
            reader = new JSONObject(jsonDefibrillator);
            internetAccessArray = reader.getJSONArray(INTERNET_OBJECT);
            for (int i = 0; i < internetAccessArray.length(); i++) {
                JSONObject internetAccessObject;
                internetAccessObject = internetAccessArray.getJSONObject(i);

                String longitude = internetAccessObject.getString(LONGITUDE);
                String latitude = internetAccessObject.getString(LATITUDE);
                String name = internetAccessObject.getString(INTERNET_NAME);
                String nature = internetAccessObject.getString(INTERNET_NATURE);
                String nbPlace = internetAccessObject.getString(INTERNET_GRATUITY);

                poiInternetAccess.addPOIDTO(new POIInternetAccessDTO(longitude, latitude, name, nature, nbPlace));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return poiInternetAccess;
    }
}
