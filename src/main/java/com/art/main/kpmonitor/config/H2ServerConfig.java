package com.art.main.kpmonitor.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.sql.SQLException;

@Slf4j
@Configuration
public class H2ServerConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        log.info("H2 TCP Server 시작 중... 포트: 9092");
        Server tcpServer = Server.createTcpServer(
            "-tcp", 
            "-tcpAllowOthers", 
            "-tcpPort", "9092",
            "-ifNotExists"  // 데이터베이스가 없으면 자동 생성
        );
        log.info("H2 TCP Server URL: {}", tcpServer.getURL());
        return tcpServer;
    }
}