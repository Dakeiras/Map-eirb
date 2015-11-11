package com.enseirb.timtim.map_eirb.converter.v2;

import com.enseirb.timtim.map_eirb.converter.IPOICollectionConverter;
import com.enseirb.timtim.map_eirb.converter.listener.IPOICollectionConverterListener;
import com.enseirb.timtim.map_eirb.dao.DefibrillatorCollectionDAO;
import com.enseirb.timtim.map_eirb.dao.ElectricCollectionDAO;
import com.enseirb.timtim.map_eirb.dao.IPOICollectionDAO;
import com.enseirb.timtim.map_eirb.dao.InternetCollectionDAO;
import com.enseirb.timtim.map_eirb.dao.ToiletCollectionDAO;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.dto.POIDTO;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public class POICollectionConverter2 implements IPOICollectionConverter {
    private IPOICollectionDAO dao;
    private IPOIConverter converter;


    public POICollectionConverter2(POIType type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //this.dao = (IPOICollectionDAO) Class.forName(type.toString()+"CollectionDAO").newInstance();
        //this.converter = (IPOIConverter) Class.forName(type.toString()+"Converter").newInstance();
        //OR :
        switch (type){
            case DEFIBRILLATOR:
                dao = new DefibrillatorCollectionDAO();
                converter = new DefibrillatorConverter();
                break;
            case INTERNET:
                dao = new InternetCollectionDAO();
                converter = new InternetConverter();
                break;
            case TOILET:
                dao = new ToiletCollectionDAO();
                converter = new ToiletConverter();
                break;
            case ELECTRIC:
                dao = new ElectricCollectionDAO();
                converter = new ElectricConverter();
                break;
            default:
                dao = null;
                converter = null;
        }
    }

    public void retrievePOICollection(final POIType type, final IPOICollectionConverterListener listener){
        dao.retrievePOICollection(new IPOICollectionDAOListener() {

            @Override
            public void onSuccess(POICollectionDTO poiCollectionDTO) {
                listener.onSuccess(convert(poiCollectionDTO));
            }

            @Override
            public void onError(String message) {
                listener.onError(message);
            }
        });
    }

    public POICollection convert(POICollectionDTO collectionDTO){
        POICollection poiCollection = new POICollection();
        for (POIDTO poi : collectionDTO.getPOICollection()){
            poiCollection.add(converter.convertDTO(poi));
        }

        return poiCollection;
    }
}
