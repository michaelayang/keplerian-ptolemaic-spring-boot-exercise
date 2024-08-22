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

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.keplerianptolemaic.model.PtolemaicRecord;

@RepositoryRestResource
public interface PtolemaicRecordRepository extends JpaRepository<PtolemaicRecord, Long>, OrbitDataWriteRepository {
    @Query(value = "SELECT id,  first_epicycle_theta, first_epicycle_radius, second_epicycle_theta, second_epicycle_radius, third_epicycle_theta, third_epicycle_radius, ptolemaic_overall_angle from public.ptolemaic WHERE id = :id", nativeQuery = true)
    public PtolemaicRecord findPtolemaicRecord(@Param("id") long id);
}
