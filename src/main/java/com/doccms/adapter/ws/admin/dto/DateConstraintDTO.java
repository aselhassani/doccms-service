package com.doccms.adapter.ws.admin.dto;

import com.doccms.domain.model.constraint.DateConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@Schema(name = "DateConstraintV1DTO", description = "Date constraint DTO")
public record DateConstraintDTO(
        LocalDate after,
        LocalDate before
) {

    public static DateConstraintDTO fromDomain(DateConstraint domain) {
        return DateConstraintDTO.builder()
                .after(domain.after())
                .before(domain.before())
                .build();
    }

    public DateConstraint toDomain() {
        return DateConstraint.builder()
                .after(this.after())
                .before(this.before())
                .build();
    }
}
