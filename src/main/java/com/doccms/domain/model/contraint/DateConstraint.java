package com.doccms.domain.model.contraint;

import java.time.LocalDate;

import lombok.Builder;


@Builder
public record DateConstraint(LocalDate after,
                             LocalDate before) {

}
