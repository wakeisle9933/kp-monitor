package com.art.main.kpmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "회원 응답 DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponse {
    
    @Schema(description = "회원 ID", example = "550e8400-e29b-41d4-a716-446655440001")
    private String id;
    
    @Schema(description = "회원 이름", example = "김철수")
    private String name;
    
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
    
    @Schema(description = "이메일", example = "kimcs@example.com")
    private String email;
    
    @Schema(description = "알림 수신 여부", example = "true")
    private Boolean alertEnabled;
    
    @Schema(description = "생성일시", example = "2024-01-01T12:00:00")
    private LocalDateTime createdAt;
    
    @Schema(description = "수정일시", example = "2024-01-01T12:00:00")
    private LocalDateTime updatedAt;
}