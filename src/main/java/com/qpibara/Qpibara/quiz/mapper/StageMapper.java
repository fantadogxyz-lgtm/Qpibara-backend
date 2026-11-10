package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.StageRequest;
import com.qpibara.Qpibara.quiz.dto.StageResponse;
import com.qpibara.Qpibara.quiz.model.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class, uses = {QuestionMapper.class})
public interface StageMapper {
    @Mapping(target = "quiz", ignore = true)
    Stage toEntity(StageRequest request);

    StageResponse toResponse(Stage stage);
}