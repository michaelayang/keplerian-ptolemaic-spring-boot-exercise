package com.keplerianptolemaic.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.keplerianptolemaic.model.TruthDataRecord;

@RepositoryRestResource
public interface TruthDataRecordRepository extends JpaRepository<TruthDataRecord, Long>, OrbitDataWriteRepository {
    @Query(value = "SELECT pg_sleep(0.1), id, truth_angle from public.truth_data WHERE id = :id", nativeQuery = true)
    public TruthDataRecord findTruthDataRecord(@Param("id") long id);
}
