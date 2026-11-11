package com.qpibara.Qpibara.event.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.event.dto.*;
import com.qpibara.Qpibara.event.model.Session;
import com.qpibara.Qpibara.event.model.Team;
import com.qpibara.Qpibara.event.model.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface SessionMapper {

    SessionResponse toResponse(Session session);

    TeamResponse toResponse(Team team);
    MemberResponse toResponse(Member member);
}