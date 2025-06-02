package com.art.main.kpmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "알림 기준 응답 DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertCriteriaResponse {
    
    @Schema(description = "알림 기준 ID", example = "660e8400-e29b-41d4-a716-446655440001")
    private String id;
    
    @Schema(description = "코인 티커", example = "USDT")
    private String coinTicker;
    
    @Schema(description = "코인 이름", example = "테더")
    private String coinName;
    
    @Schema(description = "김프 알림 기준 퍼센트 (한국이 비쌀 때)", example = "5.0")
    private BigDecimal premiumPercent;
    
    @Schema(description = "역김프 알림 기준 퍼센트 (한국이 쌀 때)", example = "-5.0")
    private BigDecimal reversePremiumPercent;
    
    @Schema(description = "생성일시", example = "2024-01-01T12:00:00")
    private LocalDateTime createdAt;
    
    @Schema(description = "수정일시", example = "2024-01-01T12:00:00")
    private LocalDateTime updatedAt;
    
}