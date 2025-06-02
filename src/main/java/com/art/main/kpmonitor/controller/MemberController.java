package com.art.main.kpmonitor.controller;

import com.art.main.kpmonitor.dto.MemberRequest;
import com.art.main.kpmonitor.dto.MemberResponse;
import com.art.main.kpmonitor.service.MemberService;
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
 * 회원 관리 API Controller
 * 회원 정보의 CRUD 및 알림 설정 관리를 담당합니다.
 */
@Tag(name = "회원 관리", description = "회원 정보 및 알림 설정 관리 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 목록 조회 (필터링 지원)
     * @param alertEnabled 알림 수신 여부 필터 (선택)
     * @param memberId 특정 회원 ID (선택)
     * @return 조건에 맞는 회원 정보 리스트
     */
    @Operation(summary = "회원 조회", description = "조건에 따라 회원 정보를 조회합니다. 파라미터 없으면 전체 조회.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers(
            @Parameter(description = "알림 수신 여부 필터", example = "true") 
            @RequestParam(required = false) Boolean alertEnabled,
            @Parameter(description = "특정 회원 ID", example = "550e8400-e29b-41d4-a716-446655440001") 
            @RequestParam(required = false) String memberId) {
        return ResponseEntity.ok(memberService.getMembers(alertEnabled, memberId));
    }


    /**
     * 신규 회원 등록
     * @param member 회원 정보
     * @return 생성된 회원 정보
     */
    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다. ID는 자동 생성됩니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "등록 성공"),
        @ApiResponse(responseCode = "400", description = "이메일 중복 등 잘못된 요청")
    })
    @PostMapping
    public ResponseEntity<MemberResponse> createMember(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "회원 정보",
                required = true,
                content = @Content(
                    examples = @ExampleObject(
                        name = "회원 등록 예시",
                        value = """
                        {
                          "name": "박영희",
                          "phoneNumber": "010-9876-5432",
                          "email": "park@example.com",
                          "alertEnabled": true
                        }"""
                    )
                )
            )
            @Valid @RequestBody MemberRequest member) {
        MemberResponse createdMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    /**
     * 회원 정보 수정
     * @param id 회원 ID
     * @param member 수정할 회원 정보
     * @return 수정된 회원 정보
     */
    @Operation(summary = "회원 정보 수정", description = "기존 회원 정보를 수정합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "수정 성공"),
        @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음"),
        @ApiResponse(responseCode = "400", description = "이메일 중복 등 잘못된 요청")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(
            @Parameter(description = "회원 ID", required = true, example = "550e8400-e29b-41d4-a716-446655440001") 
            @PathVariable String id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "수정할 회원 정보",
                required = true,
                content = @Content(
                    examples = @ExampleObject(
                        name = "회원 수정 예시 (기존 김철수 데이터)",
                        value = """
                        {
                          "name": "김철수",
                          "phoneNumber": "010-1111-2222",
                          "email": "kimcs_updated@example.com",
                          "alertEnabled": false
                        }"""
                    )
                )
            )
            @Valid @RequestBody MemberRequest member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

    /**
     * 회원 삭제
     * @param id 회원 ID
     * @return 204 No Content
     */
    @Operation(summary = "회원 삭제", description = "회원을 삭제합니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "삭제 성공"),
        @ApiResponse(responseCode = "404", description = "회원을 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(
            @Parameter(description = "회원 ID", required = true, example = "550e8400-e29b-41d4-a716-446655440001") 
            @PathVariable String id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}