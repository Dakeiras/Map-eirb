package com.enseirb.timtim.map_eirb.dao;

import com.enseirb.timtim.map_eirb.dto.DataDTO;
import com.enseirb.timtim.map_eirb.dto.ToiletsDTO;
import com.enseirb.timtim.map_eirb.parser.DataParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by air on 14/11/2015.
 */
public class ToiletsDAO extends DataParser {
    private static final String TOILET_OBJECT ="d";
    private static final String TOILET_TYPOLOGIE = "typologie";
    private static final String TOILETS_APIURL = "http://odata.bordeaux.fr/v1/databordeaux/sigsanitaire/?format=json";
    @Override
    public List<DataDTO> parseData() {
        List<DataDTO> toiletListDTO = new ArrayList<DataDTO>();
        try{
            InputStream is = connectTo(TOILETS_APIURL);
            String result = requestResponseAsString(is);
            JSONArray toiletsArray;
            JSONObject reader =new JSONObject(result);
            toiletsArray = reader.getJSONArray(TOILET_OBJECT);

            for (int i = 0; i < toiletsArray.length(); i++) {
                JSONObject toiletsAccessObject;
                toiletsAccessObject = toiletsArray.getJSONObject(i);

                String longitude = toiletsAccessObject.getString(LONGITUDE);
                String latitude = toiletsAccessObject.getString(LATITUDE);
                String typologie = toiletsAccessObject.getString(TOILET_TYPOLOGIE);

                toiletListDTO.add(new ToiletsDTO(longitude, latitude, typologie));
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return toiletListDTO;
    }
}
