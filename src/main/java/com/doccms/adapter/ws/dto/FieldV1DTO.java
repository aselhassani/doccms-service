package com.doccms.adapter.ws.dto;

import java.util.Optional;

import com.doccms.adapter.ws.dto.enums.FieldMode;
import com.doccms.adapter.ws.dto.enums.FieldType;
import com.doccms.domain.model.Field;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "FieldV1DTO", description = "Field DTO")
public record FieldV1DTO(
    String name,
    FieldType type,
    FieldMode mode,
    Object defaultValue,
    ConstraintsV1DTO constraints
) {
    public static FieldV1DTO fromDomain(Field field) {
        return FieldV1DTO.builder()
                         .name(field.name())
                         .type(FieldType.valueOf(field.type().name()))
                         .mode(FieldMode.valueOf(field.mode().name()))
                         .defaultValue(field.defaultValue())
                         .constraints(Optional.of(field.constraints())
                                              .map(ConstraintsV1DTO::fromDomain)
                                              .orElse(null))
                         .build();
    }

    public Field toDomain() {
        return Field.builder()
                    .name(this.name())
                    .type(com.doccms.domain.model.enums.FieldType.valueOf(this.type().name()))
                    .mode(com.doccms.domain.model.enums.FieldMode.valueOf(this.mode().name()))
                    .defaultValue(this.defaultValue())
                    .constraints(Optional.of(this.constraints())
                                         .map(ConstraintsV1DTO::toDomain)
                                         .orElse(null))
                    .build();
    }
}
