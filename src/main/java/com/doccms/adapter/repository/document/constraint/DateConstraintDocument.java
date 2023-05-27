package com.doccms.adapter.repository.document.constraint;

import java.time.LocalDate;

import com.doccms.domain.model.constraint.DateConstraint;
import lombok.Builder;


@Builder
public record DateConstraintDocument(LocalDate after,
                                     LocalDate before) {

    public static DateConstraintDocument fromDomain(DateConstraint domain) {
        return DateConstraintDocument.builder()
                                     .before(domain.before())
                                     .after(domain.after())
                                     .build();
    }

    public DateConstraint toDomain() {
        return DateConstraint.builder()
                             .before(this.before())
                             .after(this.after())
                             .build();
    }
}
