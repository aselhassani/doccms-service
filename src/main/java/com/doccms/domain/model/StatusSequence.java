package com.doccms.domain.model;

import lombok.Builder;

@Builder
public record StatusSequence(
        Long entryStatusId,
        Long exitStatusId,
        Long schemaId

) {


}
