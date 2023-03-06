package com.doccms.adapter.repository.document;

import java.util.Optional;

import com.doccms.adapter.repository.document.contraint.ConstraintsDocument;
import com.doccms.adapter.repository.document.enums.FieldMode;
import com.doccms.adapter.repository.document.enums.FieldType;
import com.doccms.domain.model.Field;
import lombok.Builder;

@Builder
public record FieldDocument(
    String name,
    FieldType type,
    FieldMode mode,
    Object defaultValue,
    ConstraintsDocument constraints
) {


    public static FieldDocument fromDomain(Field domain) {
        return FieldDocument.builder()
                            .name(domain.name())
                            .type(FieldType.valueOf(domain.type().name()))
                            .mode(FieldMode.valueOf(domain.mode().name()))
                            .defaultValue(domain.defaultValue())
                            .constraints(
                                Optional.of(domain.constraints()).map(ConstraintsDocument::fromDomain).orElse(null))
                            .build();
    }

    public Field toDomain() {
        return Field.builder()
                    .name(this.name())
                    .type(com.doccms.domain.model.enums.FieldType.valueOf(this.type().name()))
                    .mode(com.doccms.domain.model.enums.FieldMode.valueOf(this.mode().name()))
                    .defaultValue(this.defaultValue())
                    .constraints(
                        Optional.of(this.constraints())
                                .map(ConstraintsDocument::toDomain).orElse(null))
                    .build();
    }
}
