package com.project.give.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HealthCheckController {

    @Value("${server.env}")
    private String env;

    @Value("${server.name}")
    private String serverName;

    @Value("${server.deploy-address}")
    private String deployAddress;

    @Value("${server.port}")
    private String port;

    /**
     * 배포 헬스체크 정보
     */
    @GetMapping("/server/hc")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "serverName", serverName,
                "deployAddress", deployAddress,
                "port", port
        ));
    }

    /**
     * 현재 server env (blue | green)
     * GitHub Actions가 이 값을 읽고
     * 어떤 서버를 종료하고 어떤 서버를 띄울지 판단함.
     */
    @GetMapping("/server/env")
    public ResponseEntity<?> getEnv() {
        return ResponseEntity.ok(env);
    }
}
