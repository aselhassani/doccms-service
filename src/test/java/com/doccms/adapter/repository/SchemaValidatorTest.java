package com.doccms.adapter.repository;

import com.doccms.adapter.ws.admin.dto.enums.FieldMode;
import com.doccms.adapter.ws.admin.dto.enums.FieldType;
import com.doccms.adapter.ws.admin.exception.SchemaValidator;
import com.doccms.helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class SchemaValidatorTest {

    private final Random random = new SecureRandom();
    @InjectMocks
    private SchemaValidator underTest;
    private Integer integer;
    private Long aLong;
    private Float aFloat;
    private Double aDouble;
    private Boolean aBoolean;
    private LocalDate date;
    private String string;

    @BeforeEach
    void setup() {
        integer = random.nextInt();
        aLong = random.nextLong();
        aDouble = random.nextDouble();
        aFloat = random.nextFloat();
        aBoolean = random.nextBoolean();
        date = LocalDate.now();
        string = TestHelper.getRandomText(10);
    }

    @ParameterizedTest
    @EnumSource(FieldType.class)
    void validateFieldValueShouldValidateNullValue(FieldType type) {
        var result = underTest.validateFieldValue(null, type, TestHelper.getRandomClass(FieldMode.class));
        assertThat(result).isTrue();
    }

    @Test
    void validateFieldValueShouldValidateCorrectBooleanValue() {
        var values = List.of(Boolean.TRUE, Boolean.FALSE);
        values.stream()
                .filter(v -> !underTest.validateFieldValue(v, FieldType.BOOLEAN, FieldMode.SINGLE))
                .forEach(v -> fail("Validation error, " + v + " is a valid value for BOOLEAN type"));
    }

    @Test
    void validateFieldValueShouldNotValidateWrongBooleanValue() {
        var values = List.of(integer, aLong, aDouble, aFloat, date, string);
        values.stream()
                .filter(v -> underTest.validateFieldValue(v, FieldType.BOOLEAN, FieldMode.SINGLE))
                .forEach(v -> fail("Validation error, " + v + " is an invalid value for BOOLEAN type "));
    }

    @Test
    void validateFieldValueShouldNotValidateListOfBooleanForSingleMode() {
        var result = underTest.validateFieldValue(List.of(aBoolean), FieldType.BOOLEAN, FieldMode.SINGLE);
        assertThat(result).isFalse();
    }

    @Test
    void validateFieldValueShouldNotValidateSingleBooleanForSingleMode() {
        var result = underTest.validateFieldValue(aBoolean, FieldType.BOOLEAN, FieldMode.LIST);
        assertThat(result).isFalse();
    }


}
