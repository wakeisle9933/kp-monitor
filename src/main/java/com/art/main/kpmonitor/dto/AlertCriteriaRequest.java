package com.art.main.kpmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(description = "알림 기준 요청 DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertCriteriaRequest {
    
    @Schema(description = "코인 티커", example = "USDT", required = true)
    @NotBlank(message = "코인 티커는 필수입니다")
    private String coinTicker;
    
    @Schema(description = "코인 이름", example = "테더", required = true)
    @NotBlank(message = "코인 이름은 필수입니다")
    private String coinName;
    
    @Schema(description = "김프 알림 기준 퍼센트 (한국이 비쌀 때, 0.01 ~ 100.00)", example = "5.0")
    @DecimalMin(value = "0.01", message = "김프 기준값은 0.01 이상이어야 합니다")
    @DecimalMax(value = "100.00", message = "김프 기준값은 100.00 이하여야 합니다")
    private BigDecimal premiumPercent;
    
    @Schema(description = "역김프 알림 기준 퍼센트 (한국이 쌀 때, -100.00 ~ -0.01)", example = "-5.0")
    @DecimalMin(value = "-100.00", message = "역김프 기준값은 -100.00 이상이어야 합니다")
    @DecimalMax(value = "-0.01", message = "역김프 기준값은 -0.01 이하여야 합니다")
    private BigDecimal reversePremiumPercent;
    
}