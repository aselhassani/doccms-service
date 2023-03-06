package com.doccms.domain.model;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

import lombok.Builder;

@Builder
public record Node(
    String id,
    String parentId,
    String typeName,
    String title,
    String description,
    String owner,
    Set<String> contributors,
    String language,
    Instant createdAt,
    Instant updatedAt,
    Map<String, Object> attributes

) {
}
