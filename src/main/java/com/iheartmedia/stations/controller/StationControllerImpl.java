/**
 * Copyright (c)2018 - Michael S. Hepfer <michaelsteven@hepfer.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.iheartmedia.stations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.iheartmedia.stations.entity.Station;
import com.iheartmedia.stations.service.StationService;

/**
 * Station Controller Default Implementation
 * 
 * @author Michael_Hepfer
 *
 */
@Component
public class StationControllerImpl implements StationController {

    @Autowired
	private StationService stationService;
    
    
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.controller.StationController#getAllStations(java.lang.String, java.lang.String, java.lang.Boolean, int, int)
	 */
	@Override
	public Page<Station> getAllStations(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = PARAMETER_STATION_ID, required = false) String stationId, @RequestParam(name = PARAMETER_HD_ENABLED, required = false) Boolean hdEnabled, @RequestParam(name = PARAMETER_PAGINATION_OFFSET, defaultValue = DEFAULT_OFFSET) int offset, @RequestParam(name = PARAMETER_PAGINATION_LIMIT, defaultValue = DEFAULT_LIMIT) int limit) {
		return stationService.getAllStations(name, stationId, hdEnabled, offset, limit);
	}

	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.controller.StationController#getStation(java.lang.String)
	 */
	@Override
	public Station getStation(@PathVariable(PARAMETER_STATION_ID) String stationId) {
		return stationService.getStation(stationId);
	}

	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.controller.StationController#createStation(com.iheartmedia.stations.entity.Station)
	 */
	@Override
	public void createStation(@RequestBody Station station) {
		stationService.createStation(station);
	}

	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.controller.StationController#updateStation(com.iheartmedia.stations.entity.Station, java.lang.String)
	 */
	@Override
	public void updateStation(@RequestBody Station station, @PathVariable(PARAMETER_STATION_ID) String stationId) {
		stationService.updateStation(station, stationId);
	}

	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.controller.StationController#deleteStation(java.lang.String)
	 */
	@Override
	public void deleteStation(@PathVariable(PARAMETER_STATION_ID) String stationId) {
		stationService.deleteStation(stationId);
	}

}
