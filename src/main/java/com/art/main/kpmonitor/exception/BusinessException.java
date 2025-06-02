package com.art.main.kpmonitor.exception;

import lombok.Getter;

/**
 * 비즈니스 로직 처리 중 발생하는 예외
 * ErrorCode를 통해 일관된 예외 처리를 제공합니다.
 */
@Getter
public class BusinessException extends RuntimeException {
    
    private final ErrorCode errorCode;
    
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
    
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}