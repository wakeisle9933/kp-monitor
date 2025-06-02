-- 테스트 회원 데이터
INSERT INTO member (id, name, phone_number, email, alert_enabled, created_at, updated_at) 
VALUES ('550e8400-e29b-41d4-a716-446655440001', '김철수', '010-1234-5678', 'kimcs@example.com', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 김프 알림 기준 데이터
-- premium_percent: 한국이 비쌀 때 (예: 5% 이상일 때 알림 - 매도 타이밍)
-- reverse_premium_percent: 한국이 쌀 때 (예: -5% 이하일 때 알림 - 매수 타이밍)
INSERT INTO alert_criteria (id, coin_ticker, coin_name, premium_percent, reverse_premium_percent, created_at, updated_at) 
VALUES ('660e8400-e29b-41d4-a716-446655440001', 'USDT', '테더', 5.0, -5.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);