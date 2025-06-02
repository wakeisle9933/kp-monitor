package com.art.main.kpmonitor.controller;

import com.art.main.kpmonitor.dto.AlertCriteriaRequest;
import com.art.main.kpmonitor.dto.AlertCriteriaResponse;
import com.art.main.kpmonitor.service.AlertCriteriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 김프 알림 기준 관리 API Controller
 * 코인별 김프/역김프 알림 기준값 설정 및 관리를 담당합니다.
 * - 김프: 한국이 비쌀 때 (양수) - 매도 타이밍
 * - 역김프: 한국이 쌀 때 (음수) - 매수 타이밍
 */
@Tag(name = "알림 기준 관리", description = "김프/역김프 알림 기준 설정 API")
@RestController
@RequestMapping("/api/alert-criteria")
@RequiredArgsConstructor
public class AlertCriteriaController {

    private final AlertCriteriaService alertCriteriaService;

    /**
     * 알림 기준 목록 조회 (필터링 지원)
     * @param coinTicker 코인 티커 필터 (선택)
     * @param id 특정 알림 기준 ID (선택)
     * @return 조건에 맞는 알림 기준 리스트
     */
    @Operation(summary = "알림 기준 조회", description = "조건에 따라 알림 기준을 조회합니다. 파라미터 없으면 전체 조회.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<List<AlertCriteriaResponse>> getAlertCriteria(
            @Parameter(description = "코인 티커 필터", example = "USDT") 
            @RequestParam(required = false) String coinTicker,
            @Parameter(description = "특정 알림 기준 ID", example = "660e8400-e29b-41d4-a716-446655440001") 
            @RequestParam(required = false) String id) {
        return ResponseEntity.ok(alertCriteriaService.getAlertCriteria(coinTicker, id));
    }


    /**
     * 새로운 알림 기준 등록
     * @param alertCriteria 알림 기준 정보
     * @return 생성된 알림 기준 정보
     */
    @Operation(summary = "알림 기준 등록", description = "새로운 김프/역김프 알림 기준을 등록합니다. ID는 자동 생성됩니다.\n- premium_percent: 김프 알림 기준 (한국이 비쌀 때)\n- reverse_premium_percent: 역김프 알림 기준 (한국이 쌀 때)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "등록 성공"),
        @ApiResponse(responseCode = "400", description = "코인 티커 중복 등 잘못된 요청")
    })
    @PostMapping
    public ResponseEntity<AlertCriteriaResponse> createAlertCriteria(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "알림 기준 정보",
                required = true,
                content = @Content(
                    examples = @ExampleObject(
                        name = "비트코인 알림 기준 등록 예시",
                        value = """
                        {
                          "coinTicker": "BTC",
                          "coinName": "비트코인",
                          "premiumPercent": 3.5,
                          "reversePremiumPercent": -3.5
                        }"""
                    )
                )
            )
            @Valid @RequestBody AlertCriteriaRequest alertCriteria) {
        AlertCriteriaResponse createdCriteria = alertCriteriaService.createAlertCriteria(alertCriteria);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCriteria);
    }

    /**
     * 알림 기준 수정
     * @param id 알림 기준 ID
     * @param alertCriteria 수정할 알림 기준 정보
     * @return 수정된 알림 기준 정보
     */
    @Operation(summary = "알림 기준 수정", description = "기존 알림 기준을 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "수정 성공"),
        @ApiResponse(responseCode = "404", description = "알림 기준을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "코인 티커 중복 등 잘못된 요청")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlertCriteriaResponse> updateAlertCriteria(
            @Parameter(description = "알림 기준 ID", required = true, example = "660e8400-e29b-41d4-a716-446655440001") @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "수정할 알림 기준 정보",
                required = true,
                content = @Content(
                    examples = @ExampleObject(
                        name = "USDT 알림 기준 수정 예시 (기존 데이터)",
                        value = """
                        {
                          "coinTicker": "USDT",
                          "coinName": "테더",
                          "premiumPercent": 7.0,
                          "reversePremiumPercent": -7.0
                        }"""
                    )
                )
            )
            @Valid @RequestBody AlertCriteriaRequest alertCriteria) {
        return ResponseEntity.ok(alertCriteriaService.updateAlertCriteria(id, alertCriteria));
    }

    /**
     * 알림 기준 삭제
     * @param id 알림 기준 ID
     * @return 204 No Content
     */
    @Operation(summary = "알림 기준 삭제", description = "알림 기준을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "삭제 성공"),
        @ApiResponse(responseCode = "404", description = "알림 기준을 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlertCriteria(
            @Parameter(description = "알림 기준 ID", required = true, example = "660e8400-e29b-41d4-a716-446655440001") @PathVariable String id) {
        alertCriteriaService.deleteAlertCriteria(id);
        return ResponseEntity.noContent().build();
    }
}