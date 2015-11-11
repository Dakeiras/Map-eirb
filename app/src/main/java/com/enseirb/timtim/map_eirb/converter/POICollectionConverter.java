package com.enseirb.timtim.map_eirb.converter;

import com.enseirb.timtim.map_eirb.dao.IPOICollectionDAO;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public class POICollectionConverter implements IPOICollectionConverter{
    public void retrievePOICollection(POIType type, IPOICollectionConverterListener listener){
        IPOICollectionDAO
    }

    public POICollection convert(POICollectionDTO dto, POIType type){
        POICollection retval = null;
        switch (type){
            case DEFIBRILLATOR:
                retval = convertDefibrillator();
            case ELECTRIC:
                retval = convertElectric();
            case TOILET:
                retval = convertToilet();
            case INTERNET:
                retval = convertInternet();
            default:
                listener.onError("Unknown POI type: " + type.toString());
        }
        return retval;

    }

    private POICollection convertInternet() {

        return null;
    }

    private POICollection convertToilet() {
        return null;
    }

    private POICollection convertElectric() {
        return null;
    }

    private POICollection convertDefibrillator() {
        return null;
    }
}
