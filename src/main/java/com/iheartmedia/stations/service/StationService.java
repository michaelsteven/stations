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

import org.springframework.data.domain.Page;

import com.iheartmedia.stations.entity.Station;

/**
 * Station Service Interface
 * 
 * @author Michael_Hepfer
 *
 */
public interface StationService {

    /**
     * Get All Stations
     * 
     * @param name - (Nullable) String, part of a station name
     * @param stationId - (Nullable) String the station ID
     * @param hdEnabled -  (Nullable) Boolean (boxed) true if the station is HD enabled  
     * @param offset int - for use with pagenation.
     * @param limit  int - for use with pagenation.
     * @return Page<Station> of results.
     */
	public abstract Page<Station> getAllStations(String name, String stationId, Boolean hdEnabled, int offset, int limit);

    /**
     *  Get Station by ID
     *  
     * @param stationId  - String ID for the station (value should be a UUID, but passed in as string)
     * @return A station object
     */
	public abstract Station getStation(String stationId);

    /**
     * Create Station
     * 
     * @param station - Object of type Station.  stationId can be omitted and will be auto-populated.
     */
	public abstract void createStation(Station station);

    /**
     * Update Station
     * 
     * @param station -- Station object with modifications
     * @param stationId -- the station ID
     */
	public abstract void updateStation(Station station, String stationId);

    /**
     * Delete Station
     * 
     * @param stationId  -- the station ID
     */
	public abstract void deleteStation(String stationId);

}