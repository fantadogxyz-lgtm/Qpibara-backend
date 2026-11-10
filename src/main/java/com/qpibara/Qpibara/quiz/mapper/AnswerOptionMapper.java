package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.AnswerOptionRequest;
import com.qpibara.Qpibara.quiz.dto.AnswerOptionResponse;
import com.qpibara.Qpibara.quiz.model.AnswerOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class, uses = {AttachmentMapper.class})
public interface AnswerOptionMapper {

    @Mapping(target = "question", ignore = true)
    AnswerOption toEntity(AnswerOptionRequest request);

    AnswerOptionResponse toResponse(AnswerOption option);
}