package com.art.main.kpmonitor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "alert_enabled", nullable = false)
    @Builder.Default
    private Boolean alertEnabled = true;
}