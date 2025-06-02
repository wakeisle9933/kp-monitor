package com.art.main.kpmonitor.mapper;

import com.art.main.kpmonitor.dto.MemberRequest;
import com.art.main.kpmonitor.dto.MemberResponse;
import com.art.main.kpmonitor.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MemberMapper {
    
    MemberResponse toResponse(Member member);
    
    List<MemberResponse> toResponseList(List<Member> members);
    
    @Mapping(target = "id", ignore = true)
    Member toEntity(MemberRequest request);
}