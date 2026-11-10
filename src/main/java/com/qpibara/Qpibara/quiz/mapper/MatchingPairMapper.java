package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.MatchingPairRequest;
import com.qpibara.Qpibara.quiz.dto.MatchingPairResponse;
import com.qpibara.Qpibara.quiz.model.MatchingPair;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface MatchingPairMapper {

    @Mapping(target = "question", ignore = true)
    MatchingPair toEntity(MatchingPairRequest request);

    MatchingPairResponse toResponse(MatchingPair pair);
}