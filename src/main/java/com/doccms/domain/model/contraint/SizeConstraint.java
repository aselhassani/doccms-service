package com.doccms.domain.model.contraint;

import lombok.Builder;

@Builder
public record SizeConstraint(Integer minSize,
                             Integer maxSize) {
}
