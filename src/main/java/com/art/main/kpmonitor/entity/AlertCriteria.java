package com.art.main.kpmonitor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "alert_criteria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertCriteria extends BaseEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(name = "coin_ticker", nullable = false, length = 20)
    private String coinTicker;

    @Column(name = "coin_name", nullable = false, length = 100)
    private String coinName;

    @Column(name = "premium_percent", precision = 5, scale = 2)
    private BigDecimal premiumPercent;
    
    @Column(name = "reverse_premium_percent", precision = 5, scale = 2)
    private BigDecimal reversePremiumPercent;
}