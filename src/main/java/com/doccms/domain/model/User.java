package com.doccms.domain.model;

import java.time.Instant;

public record User 
(
     Long id,
     String login,
     String password,
     String email,
     boolean activated,
     String activationKey,
     String resetKey,
     Instant resetDate
)
{
}
