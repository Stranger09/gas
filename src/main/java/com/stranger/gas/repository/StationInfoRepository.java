package com.stranger.gas.repository;

import com.stranger.gas.model.StationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationInfoRepository extends JpaRepository<StationInfo, Long> {
}
