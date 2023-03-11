package com.doccms.adapter.ws.admin.dto;

import java.util.Optional;

import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import com.doccms.domain.model.Field;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(name = "FieldV1DTO", description = "Field DTO")
public record FieldV1DTO(

    @NotBlank
    String name,
    @NotNull
    FieldType type,
    @NotNull
    FieldMode mode,
    Object defaultValue,
    @Valid
    @NotNull
    ConstraintsV1DTO constraints
) {
    public static FieldV1DTO fromDomain(Field field) {
        return FieldV1DTO.builder()
                         .name(field.name())
                         .type(FieldType.valueOf(field.type().name()))
                         .mode(FieldMode.valueOf(field.mode().name()))
                         .defaultValue(field.defaultValue())
                         .constraints(Optional.ofNullable(field.constraints())
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
