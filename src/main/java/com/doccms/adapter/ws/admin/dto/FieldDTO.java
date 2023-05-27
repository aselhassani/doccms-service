package com.doccms.adapter.ws.admin.dto;

import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import com.doccms.domain.model.Field;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Optional;

@Builder
@Schema(name = "FieldV1DTO", description = "Field DTO")
public record FieldDTO(

        @NotBlank
        String name,
        @NotNull
        FieldType type,
        @NotNull
        FieldMode mode,
        Object defaultValue,
        @Valid
        @NotNull
        ConstraintsDTO constraints
) {
    public static FieldDTO fromDomain(Field field) {
        return FieldDTO.builder()
                .name(field.name())
                .type(FieldType.valueOf(field.type().name()))
                .mode(FieldMode.valueOf(field.mode().name()))
                .defaultValue(field.defaultValue())
                .constraints(Optional.ofNullable(field.constraints())
                        .map(ConstraintsDTO::fromDomain)
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
                        .map(ConstraintsDTO::toDomain)
                        .orElse(null))
                .build();
    }
}
