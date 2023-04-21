package com.keplerianptolemaic.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.keplerianptolemaic.model.KeplerianRecord;

@RepositoryRestResource
public interface KeplerianRecordRepository extends JpaRepository<KeplerianRecord
            , Long> {
    @Query(value = "SELECT id, earth_x, earth_y, earth_radius, earth_theta, mars_x, mars_y, mars_radius, mars_theta from public.keplerian WHERE id = :id", nativeQuery = true)
    public KeplerianRecord findKeplerianRecord(@Param("id") long id);
    
    @Query(value = "SELECT id, earth_x, earth_y, earth_radius, earth_theta, mars_x, mars_y, mars_radius, mars_theta from public.keplerian ORDER BY id", nativeQuery = true)
    public List<KeplerianRecord> findAllKeplerianRecords();
}
