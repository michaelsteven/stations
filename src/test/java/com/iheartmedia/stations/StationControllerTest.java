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
package com.iheartmedia.stations;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iheartmedia.stations.controller.StationController;
import com.iheartmedia.stations.entity.Station;
import com.iheartmedia.stations.repository.StationRepository;

/**
 * @author Michael_Hepfer
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = StationController.class, secure = false)
public class StationControllerTest {
	
	@Autowired 
	private MockMvc mockMvc;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@MockBean
	private StationRepository stationRepository;
	
	/**
	 * Test shouldGetAllStations()
	 * @throws Exception
	 */
	@Test
	public void shouldGetAllStations() throws Exception {
		
		// new up a station object
		Station station = new Station();
		station.setStationId("abc123");
		station.setCallSign("AF5M");
		station.setHdEnabled(false);
		station.setName("AF5M - Amateur radio station");
		
		// put it into a page object
		ArrayList<Station> stationList = new ArrayList<Station>();
		stationList.add(station);
		Page<Station> page = new PageImpl<Station>(stationList, PageRequest.of(0,10) ,stationList.size());
		
		// mock up the return and make the call
		when(stationRepository.findStationWithOptionalStationIdAndNameAndHdEnabled(PageRequest.of(0, 10), null, null, null)).thenReturn(page);
		MvcResult result = mockMvc.perform(get("/api/1.0/stations")).andDo(print()).andExpect(status().isOk()).andReturn();
		
		// verify that we found our object
		assertThat( result.getResponse().getContentAsString(), containsString(station.getCallSign()));
	}
	
	/**
	 * Test shouldGetStationByStationId() 
	 * @throws Exception
	 */
	@Test
	public void shouldGetStationByStationId() throws Exception{
		// new up a station object
		Station station = new Station();
		station.setStationId("abc123");
		station.setCallSign("AF5M");
		station.setHdEnabled(false);
		station.setName("AF5M - Amateur radio station");
		
		// mock up the return and make the call
		when(stationRepository.findById(Mockito.anyString())).thenReturn(Optional.of(station));
		MvcResult result = mockMvc.perform(get("/api/1.0/stations/{stationId}", station.getStationId())).andDo(print()).andExpect(status().isOk()).andReturn();
		
		// verify that we found our object
		assertThat( result.getResponse().getContentAsString(), containsString(station.getCallSign()));
	}
	
	/**
	 * Test shoudCreateNewStation()
	 * @throws Exception
	 */
	@Test
    public void shoudCreateNewStation() throws Exception{
		// new up a station object
		Station station = new Station();
		station.setStationId("abc123");
		station.setCallSign("AF5M");
		station.setHdEnabled(false);
		station.setName("AF5M - Amateur radio station");
		
		// mock up the return and make the call
		when(stationRepository.save(station)).thenReturn(station);
		mockMvc.perform(
				post("/api/1.0/stations/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(station))
					).andDo(print()).andExpect(status().isCreated());
		
	}
	
	/**
	 * Test shouldUpdateStation()
	 * @throws Exception
	 */
	@Test
    public void shouldUpdateStation() throws Exception{
		// new up a station object
		Station station = new Station();
		station.setStationId("abc123");
		station.setCallSign("AF5M");
		station.setHdEnabled(false);
		station.setName("AF5M - Amateur radio station");
		
		// mock up the return and make the call
		when(stationRepository.findById(Mockito.anyString())).thenReturn(Optional.of(station));
		station.setHdEnabled(true);
		when(stationRepository.save(station)).thenReturn(station);
		mockMvc.perform(
				put("/api/1.0/stations/{stationId}", station.getStationId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(mapper.writeValueAsString(station))
					).andDo(print()).andExpect(status().isNoContent());
		
	}
	
	/**
	 * Test shouldDeleteStation()
	 * @throws Exception
	 */
	@Test
    public void shouldDeleteStation() throws Exception{
		// mock up the return and make the call
		doNothing().when(stationRepository).deleteById("abc123");
		mockMvc.perform(delete("/api/1.0/stations/{stationId}", "abc123")).andDo(print()).andExpect(status().isNoContent());
	}
	
}
