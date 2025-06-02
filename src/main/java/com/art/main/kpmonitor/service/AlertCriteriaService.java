package com.art.main.kpmonitor.service;

import com.art.main.kpmonitor.dto.AlertCriteriaRequest;
import com.art.main.kpmonitor.dto.AlertCriteriaResponse;
import com.art.main.kpmonitor.entity.AlertCriteria;
import com.art.main.kpmonitor.exception.BusinessException;
import com.art.main.kpmonitor.exception.ErrorCode;
import com.art.main.kpmonitor.mapper.AlertCriteriaMapper;
import com.art.main.kpmonitor.repository.AlertCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlertCriteriaService {

    private final AlertCriteriaRepository alertCriteriaRepository;
    private final AlertCriteriaMapper alertCriteriaMapper;

    public List<AlertCriteriaResponse> getAlertCriteria(String coinTicker, String id) {
        // 특정 ID 조회가 우선
        if (id != null) {
            AlertCriteria criteria = alertCriteriaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(ErrorCode.ALERT_CRITERIA_NOT_FOUND));
            return List.of(alertCriteriaMapper.toResponse(criteria));
        }
        
        // 코인 티커 필터
        if (coinTicker != null) {
            AlertCriteria criteria = alertCriteriaRepository.findByCoinTicker(coinTicker)
                    .orElseThrow(() -> new BusinessException(ErrorCode.ALERT_CRITERIA_NOT_FOUND));
            return List.of(alertCriteriaMapper.toResponse(criteria));
        }
        
        // 필터 없으면 전체 조회
        return alertCriteriaMapper.toResponseList(alertCriteriaRepository.findAll());
    }

    @Transactional
    public AlertCriteriaResponse createAlertCriteria(AlertCriteriaRequest request) {
        if (alertCriteriaRepository.existsByCoinTicker(request.getCoinTicker())) {
            throw new BusinessException(ErrorCode.DUPLICATE_COIN_TICKER);
        }
        
        AlertCriteria alertCriteria = alertCriteriaMapper.toEntity(request);
        alertCriteria.setId(UUID.randomUUID().toString());
        
        AlertCriteria savedCriteria = alertCriteriaRepository.save(alertCriteria);
        return alertCriteriaMapper.toResponse(savedCriteria);
    }

    @Transactional
    public AlertCriteriaResponse updateAlertCriteria(String id, AlertCriteriaRequest request) {
        AlertCriteria alertCriteria = alertCriteriaRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ALERT_CRITERIA_NOT_FOUND));
        
        if (!alertCriteria.getCoinTicker().equals(request.getCoinTicker()) 
            && alertCriteriaRepository.existsByCoinTicker(request.getCoinTicker())) {
            throw new BusinessException(ErrorCode.DUPLICATE_COIN_TICKER);
        }
        
        alertCriteria.setCoinTicker(request.getCoinTicker());
        alertCriteria.setCoinName(request.getCoinName());
        alertCriteria.setPremiumPercent(request.getPremiumPercent());
        alertCriteria.setReversePremiumPercent(request.getReversePremiumPercent());
        
        AlertCriteria updatedCriteria = alertCriteriaRepository.save(alertCriteria);
        return alertCriteriaMapper.toResponse(updatedCriteria);
    }

    @Transactional
    public void deleteAlertCriteria(String id) {
        if (!alertCriteriaRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.ALERT_CRITERIA_NOT_FOUND);
        }
        alertCriteriaRepository.deleteById(id);
    }
}