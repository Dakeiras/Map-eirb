package com.enseirb.timtim.map_eirb.dao;

import java.io.IOException;
import java.io.InputStream;

import com.enseirb.timtim.map_eirb.dto.DataDTO;
import com.enseirb.timtim.map_eirb.dto.InternetAccessDTO;
import com.enseirb.timtim.map_eirb.parser.DataParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by air on 14/11/2015.
 */
public class InternetAccessDAO extends DataParser {
    private static final String INTERNET_OBJECT ="d";
    private static final String INTERNET_NAME = "nom";
    private static final String INTERNET_NATURE = "type_acces";
    private static final String INTERNET_GRATUITY = "payant";
    private static final String INTERNET_APIURL = "http://odata.bordeaux.fr/v1/databordeaux/sigaccesinternet/?format=json";

    @Override
    public List<DataDTO> parseData() {
        List<DataDTO> internetAccessListDTO = new ArrayList<DataDTO>();
        try{
            InputStream is = connectTo(INTERNET_APIURL);
            String result = requestResponseAsString(is);
            JSONArray internetAccessArray;
            JSONObject reader =new JSONObject(result);
            internetAccessArray = reader.getJSONArray(INTERNET_OBJECT);

            for (int i = 0; i < internetAccessArray.length(); i++) {
                JSONObject internetAccessObject;
                    internetAccessObject = internetAccessArray.getJSONObject(i);

                    String longitude = internetAccessObject.getString(LONGITUDE);
                    String latitude = internetAccessObject.getString(LATITUDE);
                    String name = internetAccessObject.getString(INTERNET_NAME);
                    String nature = internetAccessObject.getString(INTERNET_NATURE);
                    String nbPlace = internetAccessObject.getString(INTERNET_GRATUITY);

                    internetAccessListDTO.add(new InternetAccessDTO(longitude, latitude, name, nature , nbPlace));
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return internetAccessListDTO;
    }

}
