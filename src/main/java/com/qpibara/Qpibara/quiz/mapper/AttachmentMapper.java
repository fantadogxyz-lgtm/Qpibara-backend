package com.qpibara.Qpibara.quiz.mapper;

import com.qpibara.Qpibara.config.CustomMapperConfig;
import com.qpibara.Qpibara.quiz.dto.AttachmentRequest;
import com.qpibara.Qpibara.quiz.dto.AttachmentResponse;
import com.qpibara.Qpibara.quiz.model.Attachment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CustomMapperConfig.class)
public interface AttachmentMapper {

    @Mapping(target = "question", ignore = true)
    @Mapping(target = "answerOption", ignore = true)
    @Mapping(target = "type.id", source = "typeId")
    Attachment toEntity(AttachmentRequest request);

    @Mapping(target = "typeId", source = "type.id")
    @Mapping(target = "typeTitle", source = "type.title")
    AttachmentResponse toResponse(Attachment attachment);
}