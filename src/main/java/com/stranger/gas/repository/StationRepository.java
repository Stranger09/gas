package com.stranger.gas.repository;

import java.util.Optional;

import com.stranger.gas.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

    Optional<Station> findStationByName(String name);

}
