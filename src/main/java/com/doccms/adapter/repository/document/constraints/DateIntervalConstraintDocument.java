package com.doccms.adapter.repository.document.constraints;

import java.time.LocalDate;

public record DateIntervalConstraintDocument(
    LocalDate start,
    LocalDate end
) {

}
