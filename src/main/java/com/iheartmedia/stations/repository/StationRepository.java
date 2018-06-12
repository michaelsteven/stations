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
package com.iheartmedia.stations.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.iheartmedia.stations.entity.Station;

@Transactional
public interface StationRepository extends JpaRepository<Station, String> {
    @Query("select station from Station station where "
    		+ "(:stationId is null or station.stationId like %:stationId%) and "
    		+ "(:name is null or station.name like %:name%) and "
    		+ "(:hdEnabled = null or station.hdEnabled = :hdEnabled)")
    public Page<Station> findStationWithOptionalStationIdAndNameAndHdEnabled(Pageable pagable,
    		@Param("stationId") String stationId, 
    		@Param("name") String name, 
    		@Param("hdEnabled") Boolean hdEnabled);
}
