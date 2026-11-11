package com.qpibara.Qpibara.event.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.event.dto.EventRequest;
import com.qpibara.Qpibara.event.dto.EventResponse;
import com.qpibara.Qpibara.event.dto.EventShortInfoResponse;
import com.qpibara.Qpibara.event.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface EventMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Event toEntity(EventRequest request);

    @Mapping(target = "quizName", ignore = true)
    EventResponse toResponse(Event event);

    EventShortInfoResponse toShortInfo(Event event);
}