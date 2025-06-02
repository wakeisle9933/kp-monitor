package com.art.main.kpmonitor.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    
    // 공통 에러
    INVALID_INPUT_VALUE("C001", "잘못된 입력값입니다", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("C002", "서버 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    
    // 회원 관련 에러
    MEMBER_NOT_FOUND("M001", "회원을 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("M002", "이미 존재하는 이메일입니다", HttpStatus.CONFLICT),
    
    // 알림 기준 관련 에러
    ALERT_CRITERIA_NOT_FOUND("A001", "알림 기준을 찾을 수 없습니다", HttpStatus.NOT_FOUND),
    DUPLICATE_COIN_TICKER("A002", "이미 존재하는 코인 티커입니다", HttpStatus.CONFLICT);
    
    private final String code;
    private final String message;
    private final HttpStatus status;
}