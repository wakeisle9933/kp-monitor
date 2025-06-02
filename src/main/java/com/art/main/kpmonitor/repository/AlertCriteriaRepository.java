package com.art.main.kpmonitor.repository;

import com.art.main.kpmonitor.entity.AlertCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlertCriteriaRepository extends JpaRepository<AlertCriteria, String> {
    
    Optional<AlertCriteria> findByCoinTicker(String coinTicker);
    
    boolean existsByCoinTicker(String coinTicker);
}