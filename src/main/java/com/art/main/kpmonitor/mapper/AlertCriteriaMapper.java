package com.art.main.kpmonitor.mapper;

import com.art.main.kpmonitor.dto.AlertCriteriaRequest;
import com.art.main.kpmonitor.dto.AlertCriteriaResponse;
import com.art.main.kpmonitor.entity.AlertCriteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AlertCriteriaMapper {
    
    AlertCriteriaResponse toResponse(AlertCriteria alertCriteria);
    
    List<AlertCriteriaResponse> toResponseList(List<AlertCriteria> alertCriteriaList);
    
    @Mapping(target = "id", ignore = true)
    AlertCriteria toEntity(AlertCriteriaRequest request);
}