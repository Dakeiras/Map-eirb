package com.enseirb.timtim.map_eirb.converter;

import com.enseirb.timtim.map_eirb.converter.listener.IPOICollectionConverterListener;
import com.enseirb.timtim.map_eirb.dao.IPOICollectionDAO;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.dto.POIDTO;
import com.enseirb.timtim.map_eirb.model.DefibrillatorPOI;
import com.enseirb.timtim.map_eirb.model.ElectricPOI;
import com.enseirb.timtim.map_eirb.model.InternetPOI;
import com.enseirb.timtim.map_eirb.model.POI;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;
import com.enseirb.timtim.map_eirb.model.ToiletPOI;

public class POICollectionConverter implements IPOICollectionConverter{
    private IPOICollectionDAO dao;


    public POICollectionConverter() {
        //this.dao = new POICollectionDAO();
    }

    public void retrievePOICollection(final POIType type, final IPOICollectionConverterListener listener){
        dao.retrievePOICollection(new IPOICollectionDAOListener() {

            @Override
            public void onSuccess(POICollectionDTO poiCollectionDTO) {
                listener.onSuccess(convert(poiCollectionDTO, type));
            }

            @Override
            public void onError(String message) {
                listener.onError(message);
            }
        });
    }

    public POICollection convert(POICollectionDTO dto, POIType type){
        POICollection retval;
        switch (type){
            case DEFIBRILLATOR:
                retval = convertDefibrillator(dto);
                break;
            case ELECTRIC:
                retval = convertElectric(dto);
                break;
            case TOILET:
                retval = convertToilet(dto);
                break;
            case INTERNET:
                retval = convertInternet(dto);
                break;
            default:
                throw new IllegalArgumentException("Unknown POI type: " + type.toString());
        }
        return retval;

    }

    private POICollection convertInternet(POICollectionDTO collectionDTO) {
        POICollection poiCollection = new POICollection();
        for (int i = 0; i < collectionDTO.size(); i++) {
            POIDTO dto = collectionDTO.get(i);
            POI poi = new InternetPOI();
            //TODO: convert dto into poi
            poiCollection.add(poi);
        }
        return poiCollection;
    }

    private POICollection convertToilet(POICollectionDTO collectionDTO) {
        POICollection poiCollection = new POICollection();
        for (int i = 0; i < collectionDTO.size(); i++) {
            POIDTO dto = collectionDTO.get(i);
            POI poi = new ToiletPOI();
            //TODO: convert dto into poi
            poiCollection.add(poi);
        }
        return poiCollection;
    }

    private POICollection convertElectric(POICollectionDTO collectionDTO) {
        POICollection poiCollection = new POICollection();
        for (int i = 0; i < collectionDTO.size(); i++) {
            POIDTO dto = collectionDTO.get(i);
            POI poi = new ElectricPOI();
            //TODO: convert dto into poi
            poiCollection.add(poi);
        }
        return poiCollection;
    }

    private POICollection convertDefibrillator(POICollectionDTO collectionDTO) {
        POICollection poiCollection = new POICollection();
        for (int i = 0; i < collectionDTO.size(); i++) {
            POIDTO dto = collectionDTO.get(i);
            POI poi = new DefibrillatorPOI();
            //TODO: convert dto into poi
            poiCollection.add(poi);
        }
        return poiCollection;
    }
}