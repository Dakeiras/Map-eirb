package com.enseirb.timtim.map_eirb.converter;

import com.enseirb.timtim.map_eirb.converter.listener.IPOICollectionConverterListener;
import com.enseirb.timtim.map_eirb.dao.IPOICollectionDAO;
import com.enseirb.timtim.map_eirb.dao.listener.IPOICollectionDAOListener;
import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;
import com.enseirb.timtim.map_eirb.dto.POIDTO;
import com.enseirb.timtim.map_eirb.model.POICollection;
import com.enseirb.timtim.map_eirb.model.POIType;

public class POICollectionConverter implements com.enseirb.timtim.map_eirb.converter.IPOICollectionConverter {
    private IPOICollectionDAO dao;
    private IPOIsConverter converter;

        /**
         * Return a new innstance of the wanted class given its type.
         * @param packagePath The path of the package in which the class is
         * @param poiType The type of the POI
         * @param suffix The suffix of the className. Can whether be "CollectionConverter" or "CollectionDAO"
         * @return an instance of the wanted class
         */
        private Object getInstanceFromClassName(String packagePath, POIType poiType, String suffix){
            String className = poiType.toString().toLowerCase();
            className = className.substring(0, 1).toUpperCase() + className.substring(1);
            String classPath = packagePath + "." + className;
            classPath += suffix;
            Object retVal = null;
            try {
                retVal = Class.forName(classPath).newInstance();
            } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                System.err.println("Class " + classPath + "does not exist");
                e.printStackTrace();
                System.exit(1);
            }
            return retVal;
        }

        private IPOICollectionDAO getDaoCollectionFromName(POIType poiType){
            return (IPOICollectionDAO) getInstanceFromClassName(
                    "com.enseirb.timtim.map_eirb.dao", poiType, "CollectionDAO");
        }

        private IPOIsConverter getConvertercollectionFromName(POIType poiType){
            return (IPOIsConverter) getInstanceFromClassName(
                    "com.enseirb.timtim.map_eirb.converter", poiType, "CollectionConverter");

        }



    public POICollectionConverter(POIType type) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        type = POIType.DEFIBRILLATOR;
        this.dao = getDaoCollectionFromName(type);
        this.converter  =getConvertercollectionFromName(type);
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
