package com.doccms.domain.model.contraint;

import lombok.Builder;

@Builder
public record NumberConstraint(
    Double minValue,
    Double maxValue

) {

}
