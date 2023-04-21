package com.keplerianptolemaic.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.keplerianptolemaic.model.PtolemaicRecord;

@RepositoryRestResource
public interface PtolemaicRecordRepository extends JpaRepository<PtolemaicRecord
            , Long> {
    @Query(value = "SELECT id,  first_epicycle_theta, first_epicycle_radius, second_epicycle_theta, second_epicycle_radius, third_epicycle_theta, third_epicycle_radius, ptolemaic_overall_angle from public.ptolemaic WHERE id = :id", nativeQuery = true)
    public PtolemaicRecord findPtolemaicRecord(@Param("id") long id);
}
