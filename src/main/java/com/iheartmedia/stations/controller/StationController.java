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

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iheartmedia.stations.entity.Station;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Station Controller Interface
 * 
 * Defines the API contract
 * 
 * @author Michael_Hepfer
 *
 */
@RestController
@Api(value = "/api/1.0/stations")
@RequestMapping("/api/1.0/stations")
public interface StationController {
	
    String DEFAULT_OFFSET = "0";
    String DEFAULT_LIMIT = "10";
    String DEFAULT_STATE = "ALL";
    String DEFAULT_CONTENT_TYPE = "application/json";
	
    String PARAMETER_NAME = "name";
    String PATH_URI_ALL_STATIONS = "";
    
    String PARAMETER_HD_ENABLED = "hdEnabled";
    
    String PARAMETER_PAGINATION_OFFSET = "offset";
    String PARAMETER_PAGINATION_LIMIT = "limit";
	
    String PARAMETER_STATION_ID = "stationId";
	String PATH_URI_STATION_ID = "/{stationId}";
	
	
    /**
     * Get All Stations
     * If no defaults are specified, it will return the first 10 results
     * 
     * @param name - (Optional) String, part of a station name
     * @param stationId - (Optional) String the station ID
     * @param hdEnabled - (Optional) Boolean (boxed/nullable) true if the station is HD enabled  
     * @param offset int - (Optional) for use with pagenation. Default is 0.
     * @param limit  int - (Optional) for use with pagenation. Default is 10
     * @return Page of object Station containing results.
     * 
     * TODO: Implement sorting
     */
    @ApiOperation(value = "Get Stations", nickname = "Get Stations", notes = "Gets a paged result of stations using the optional pagination offset and limit provided. " 
            + "The default limit is " + DEFAULT_LIMIT + " records. "
    		+ "An optional querystring parameter of '" + PARAMETER_NAME + "' is available to find a station by name.  "
    	    + "An optional querystring parameter of '" + PARAMETER_STATION_ID + "' is available to find a station by ID."
    	    + "An optional querystring parameter of '" + PARAMETER_HD_ENABLED + "' is available to search for HD enabled stations" 
    	    , response = Page.class)
    @RequestMapping(method = RequestMethod.GET, value = PATH_URI_ALL_STATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    public abstract Page<Station> getAllStations(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = PARAMETER_STATION_ID, required = false) String stationId, @RequestParam(name = PARAMETER_HD_ENABLED, required = false) Boolean hdEnabled, @RequestParam(name = PARAMETER_PAGINATION_OFFSET, defaultValue = DEFAULT_OFFSET) int offset, @RequestParam(name = PARAMETER_PAGINATION_LIMIT, defaultValue = DEFAULT_LIMIT) int limit);


    /**
     *  Get Station by ID
     *  
     * @param stationId  - String ID for the station (value should be a UUID, but passed in as string)
     * @return A station object
     */
    @ApiOperation(value = "Get Station (by ID)", nickname = "Get Station (by ID)", notes = "Returns the stations information for the given " + PARAMETER_STATION_ID + " path variable", response = Station.class)
    @RequestMapping(method = RequestMethod.GET, path = PATH_URI_STATION_ID)
	public abstract Station getStation(@PathVariable(PARAMETER_STATION_ID) String stationId);
    
    
    /**
     * Create Station
     * 
     * @param station - Object of type Station.  stationId can be omitted and will be auto-populated.
     */
    @ApiOperation(value = "Create Station", nickname = "Create Station", notes = "Creates a station")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public abstract void createStation(@RequestBody Station station);
    
    /**
     * Update Station
     * 
     * @param station -- Station object with modifications
     * @param stationId -- the station ID
     */
    @ApiOperation(value = "Update Station", nickname = "Update Station", notes = "Updates the Station for the given " + PARAMETER_STATION_ID + " path variable")
    @RequestMapping(method = RequestMethod.PUT, path = PATH_URI_STATION_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public abstract void updateStation(@RequestBody Station station, @PathVariable(PARAMETER_STATION_ID) String stationId);
    
    
    /**
     * Delete Station
     * 
     * @param stationId  -- the station ID
     */
    @ApiOperation(value = "Delete Station", nickname = "Delete Station", notes = "Deletes the station for the given " + PARAMETER_STATION_ID + " path variable")
    @RequestMapping(method = RequestMethod.DELETE, path = PATH_URI_STATION_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public abstract void deleteStation(@PathVariable(PARAMETER_STATION_ID) String stationId);
    
}
