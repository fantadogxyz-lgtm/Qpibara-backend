package com.qpibara.Qpibara.config;
import org.mapstruct.*;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.WARN,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValueMapMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        injectionStrategy =InjectionStrategy.CONSTRUCTOR
)
public interface CustomMapperConfig {
}
