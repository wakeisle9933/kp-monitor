package com.art.main.kpmonitor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "회원 요청 DTO")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequest {
    
    @Schema(description = "회원 이름", example = "김철수", required = true)
    @NotBlank(message = "이름은 필수입니다")
    private String name;
    
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
    
    @Schema(description = "이메일", example = "kimcs@example.com", required = true)
    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "올바른 이메일 형식이 아닙니다")
    private String email;
    
    @Schema(description = "알림 수신 여부", example = "true", defaultValue = "true")
    @NotNull(message = "알림 수신 여부는 필수입니다")
    @Builder.Default
    private Boolean alertEnabled = true;
    
}