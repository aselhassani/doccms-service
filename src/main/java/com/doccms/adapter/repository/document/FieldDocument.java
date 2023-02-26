package com.doccms.adapter.repository.document;

import java.util.List;

import com.doccms.adapter.repository.document.constraints.DateIntervalConstraintDocument;
import com.doccms.adapter.repository.document.constraints.NumericIntervalConstraintDocument;
import com.doccms.adapter.repository.document.constraints.PatternConstraintDocument;
import com.doccms.adapter.repository.document.enums.FieldTypeDoument;
import com.doccms.domain.model.Field;
import lombok.Builder;

@Builder
public record FieldDocument(
    String id,
    String name,
    FieldTypeDoument type,
    String defaultValue,
    Boolean nullable,
    DateIntervalConstraintDocument dateIntervalConstraint,
    NumericIntervalConstraintDocument numericIntervalConstraint,
    Integer maxLength,
    PatternConstraintDocument patternConstraint,
    List<String> possibleValues
) {

    public Field toDomain() {

        return Field.builder()
            .constraints()
            .defaultValue(this.defaultValue())
                    .build();

    }


}
