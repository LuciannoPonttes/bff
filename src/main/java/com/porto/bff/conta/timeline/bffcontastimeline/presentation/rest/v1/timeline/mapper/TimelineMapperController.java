package com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.mapper;

import com.porto.bff.conta.timeline.bffcontastimeline.application.service.timeline.model.TimelineIaasResponse;
import com.porto.bff.conta.timeline.bffcontastimeline.presentation.rest.v1.timeline.dto.TimelineIaasResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimelineMapperController {


    TimelineIaasResponseDto toTimelineIaasResponseDto(TimelineIaasResponse response);
}
