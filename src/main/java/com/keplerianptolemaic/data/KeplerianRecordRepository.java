// Copyright (C) 2023-2024, M. Yang
//
//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU General Public License as published by
//     the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.
//
//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU General Public License for details.
//
//     For further details, please see the README.md file included
//     with this software.

package com.keplerianptolemaic.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.keplerianptolemaic.model.KeplerianRecord;


@RepositoryRestResource
public interface KeplerianRecordRepository extends JpaRepository<KeplerianRecord, Long>, OrbitDataWriteRepository {

    @Query(value = "SELECT id, earth_x, earth_y, earth_radius, earth_theta, mars_x, mars_y, mars_radius, mars_theta from public.keplerian WHERE id = :id", nativeQuery = true)
    public KeplerianRecord findKeplerianRecord(@Param("id") long id);
    
    @Query(value = "SELECT id, earth_x, earth_y, earth_radius, earth_theta, mars_x, mars_y, mars_radius, mars_theta from public.keplerian ORDER BY id", nativeQuery = true)
    public List<KeplerianRecord> findAllKeplerianRecords();
}
