package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.QuizRequest;
import com.qpibara.Qpibara.quiz.dto.QuizResponse;
import com.qpibara.Qpibara.quiz.dto.QuizShortInfoResponse;
import com.qpibara.Qpibara.quiz.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class, uses = {StageMapper.class})
public interface QuizMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "stages", ignore = true)
    Quiz toEntity(QuizRequest request);

    QuizResponse toResponse(Quiz quiz);

    QuizShortInfoResponse toShortInfo(Quiz quiz);
}