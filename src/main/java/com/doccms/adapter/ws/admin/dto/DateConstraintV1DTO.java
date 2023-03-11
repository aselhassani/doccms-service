package com.doccms.adapter.ws.admin.dto;

import java.time.LocalDate;

import com.doccms.domain.model.constraint.DateConstraint;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "DateConstraintV1DTO", description = "Date constraint DTO")
public record DateConstraintV1DTO(
    LocalDate after,
    LocalDate before
) {

    public static DateConstraintV1DTO fromDomain(DateConstraint domain) {
        return DateConstraintV1DTO.builder()
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
