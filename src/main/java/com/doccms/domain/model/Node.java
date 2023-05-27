package com.doccms.domain.model;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

import com.doccms.domain.model.enums.Permission;
import lombok.Builder;

@Builder
public record Node (
        Long id,
        String parentId,
        String schemaName,
        String title,
        String description,
        String owner,
        Map<String, Permission> permissions,
        boolean sharedWithAll,
        String language,
        Instant createdAt,
        Instant updatedAt,
        Status status,
        Map<String, Object> attributes
) {
    public boolean hasPermission(String login,Permission permission){
        return (permissions.containsKey(login) && permissions.get(login).equals(permission))
                || (sharedWithAll && permission.equals(Permission.VIEW));
    }

}
