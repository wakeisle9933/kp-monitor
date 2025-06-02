package com.art.main.kpmonitor.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 에러 응답 DTO
 * 일관된 에러 응답 형식을 제공합니다.
 */
@Schema(description = "에러 응답")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    
    @Schema(description = "에러 코드", example = "M001")
    private String code;
    
    @Schema(description = "에러 메시지", example = "회원을 찾을 수 없습니다")
    private String message;
    
    @Schema(description = "에러 발생 시간", example = "2024-01-01T12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    
    @Schema(description = "요청 경로", example = "/api/members/123")
    private String path;
    
    // 단순 에러 응답 생성
    public static ErrorResponse of(String code, String message, String path) {
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .timestamp(LocalDateTime.now())
                .path(path)
                .build();
    }
}