package com.enseirb.timtim.map_eirb.parser;

import com.enseirb.timtim.map_eirb.dto.POICollectionDTO;

public interface IPOICollectionParser {
    POICollectionDTO parse(String jsonDefibrillator);
}
