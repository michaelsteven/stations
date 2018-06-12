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
package com.iheartmedia.stations.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.iheartmedia.stations.entity.Station;
import com.iheartmedia.stations.repository.StationRepository;

/**
 * Station Service Default Implementation
 * @author Michael_Hepfer
 *
 */
@Component
public class StationServiceImpl implements StationService {
	
    private static final Logger logger = LoggerFactory.getLogger(StationServiceImpl.class.getName());

    @Autowired
	private StationRepository stationRepository;
    
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.service.StationService#getAllStations(java.lang.String, java.lang.String, java.lang.Boolean, int, int)
	 */
	@Override
	public Page<Station> getAllStations(String name, String stationId, Boolean hdEnabled, int offset, int limit) {
		logger.trace("entered getAllStations()");
		Page<Station> stationPage = stationRepository.findStationWithOptionalStationIdAndNameAndHdEnabled(PageRequest.of(offset/limit, limit), stationId, name, hdEnabled);
		logger.trace("exiting getAllStations()");
		return stationPage;
	}
	
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.service.StationService#getStation(java.lang.String)
	 */
	@Override
	public Station getStation(String stationId) {
		logger.trace("entered getStation()");
		Optional<Station> station = stationRepository.findById(stationId);
		logger.trace("exiting getStation()");
		return station.get();
	}
	
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.service.StationService#createStation(com.iheartmedia.stations.entity.Station)
	 */
	@Override
	public void createStation(Station station) {
		logger.trace("entered createStation()");
		//TODO add validation
		// save the object
		stationRepository.save(station);
		logger.trace("exiting createStation()");
	}
	
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.service.StationService#updateStation(com.iheartmedia.stations.entity.Station, java.lang.String)
	 */
	@Override
	public void updateStation(Station station, String stationId) {
		logger.trace("entered updateStation()");
		//TODO add validation
		
		// retrieve the original object.  Will throw an exception if object is not found
		Optional<Station> optionalStation = stationRepository.findById(stationId);
		Station retrievedStation = optionalStation.get();
		
		// transform to the new object.
		retrievedStation.setName(station.getName());
		retrievedStation.setHdEnabled(station.isHdEnabled());
		retrievedStation.setCallSign(station.getCallSign());
		
		// save the changes
		stationRepository.save(retrievedStation);
		logger.trace("exiting updateStation()");
	}
	
	/* (non-Javadoc)
	 * @see com.iheartmedia.stations.service.StationService#deleteStation(java.lang.String)
	 */
	@Override
	public void deleteStation(String stationId) {
		logger.trace("entered deleteStation()");
		stationRepository.deleteById(stationId);
		logger.trace("exiting deleteStation()");
	}
}
