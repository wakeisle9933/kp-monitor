package com.art.main.kpmonitor.service;

import com.art.main.kpmonitor.dto.MemberRequest;
import com.art.main.kpmonitor.dto.MemberResponse;
import com.art.main.kpmonitor.entity.Member;
import com.art.main.kpmonitor.exception.BusinessException;
import com.art.main.kpmonitor.exception.ErrorCode;
import com.art.main.kpmonitor.mapper.MemberMapper;
import com.art.main.kpmonitor.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public List<MemberResponse> getMembers(Boolean alertEnabled, String memberId) {
        // 특정 회원 ID 조회가 우선
        if (memberId != null) {
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
            return List.of(memberMapper.toResponse(member));
        }
        
        // 알림 활성화 여부 필터
        if (alertEnabled != null) {
            if (alertEnabled) {
                return memberMapper.toResponseList(memberRepository.findByAlertEnabledTrue());
            } else {
                return memberMapper.toResponseList(memberRepository.findByAlertEnabledFalse());
            }
        }
        
        // 필터 없으면 전체 조회
        return memberMapper.toResponseList(memberRepository.findAll());
    }

    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
        
        Member member = memberMapper.toEntity(request);
        member.setId(UUID.randomUUID().toString());
        
        Member savedMember = memberRepository.save(member);
        return memberMapper.toResponse(savedMember);
    }

    @Transactional
    public MemberResponse updateMember(String id, MemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));
        
        if (!member.getEmail().equals(request.getEmail()) 
            && memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }
        
        member.setName(request.getName());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setEmail(request.getEmail());
        member.setAlertEnabled(request.getAlertEnabled());
        
        Member updatedMember = memberRepository.save(member);
        return memberMapper.toResponse(updatedMember);
    }

    @Transactional
    public void deleteMember(String id) {
        if (!memberRepository.existsById(id)) {
            throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
        }
        memberRepository.deleteById(id);
    }
}