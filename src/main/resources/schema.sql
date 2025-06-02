-- 기존 테이블 삭제 (외래키 순서 고려)
DROP TABLE IF EXISTS alert_criteria;
DROP TABLE IF EXISTS member;

-- 회원 테이블
CREATE TABLE IF NOT EXISTS member (
    id VARCHAR(36) PRIMARY KEY COMMENT '회원 고유 ID (UUID)',
    name VARCHAR(100) NOT NULL COMMENT '회원 이름',
    phone_number VARCHAR(20) COMMENT '전화번호',
    email VARCHAR(255) NOT NULL UNIQUE COMMENT '이메일 (중복 불가)',
    alert_enabled BOOLEAN NOT NULL DEFAULT TRUE COMMENT '알림 수신 여부 (true: 수신, false: 미수신)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT = '김프 알림을 받을 회원 정보';

-- 김프 알림 기준 테이블
CREATE TABLE IF NOT EXISTS alert_criteria (
    id VARCHAR(36) PRIMARY KEY COMMENT '알림 기준 고유 ID (UUID)',
    coin_ticker VARCHAR(20) NOT NULL COMMENT '코인 티커 (예: USDT, BTC)',
    coin_name VARCHAR(100) NOT NULL COMMENT '코인 이름 (예: 테더, 비트코인)',
    premium_percent DECIMAL(5,2) COMMENT '김프 알림 기준 퍼센트 (양수, 한국이 비쌀 때 - 매도 시점)',
    reverse_premium_percent DECIMAL(5,2) COMMENT '역김프 알림 기준 퍼센트 (음수, 한국이 쌀 때 - 매수 시점)',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT = '코인별 김프/역김프 알림 기준 설정';

-- 인덱스 추가
CREATE INDEX IF NOT EXISTS idx_member_email ON member(email);
CREATE INDEX IF NOT EXISTS idx_alert_criteria_ticker ON alert_criteria(coin_ticker);