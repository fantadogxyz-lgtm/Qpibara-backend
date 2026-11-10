package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.QuestionRequest;
import com.qpibara.Qpibara.quiz.dto.QuestionResponse;
import com.qpibara.Qpibara.quiz.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class, uses = {
        AnswerOptionMapper.class,
        MatchingPairMapper.class,
        AttachmentMapper.class
})
public interface QuestionMapper {

    @Mapping(target = "stage", ignore = true)
    Question toEntity(QuestionRequest request);

    QuestionResponse toResponse(Question question);
}