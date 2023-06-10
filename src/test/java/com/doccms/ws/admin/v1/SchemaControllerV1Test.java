package com.doccms.ws.admin.v1;

import com.doccms.adapter.ws.admin.SchemaController;
import com.doccms.adapter.ws.admin.dto.FieldDTO;
import com.doccms.adapter.ws.admin.dto.SchemaDTO;
import com.doccms.adapter.ws.admin.exception.InvalidConstraintsException;
import com.doccms.adapter.ws.admin.exception.InvalidFieldValueException;
import com.doccms.adapter.ws.admin.exception.SchemaNameExistsException;
import com.doccms.adapter.ws.FieldValidator;
import com.doccms.domain.model.Schema;
import com.doccms.domain.service.SchemaService;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.helpers.DtoTestHelper;
import com.doccms.helpers.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchemaControllerV1Test {

    @InjectMocks
    private SchemaController underTest;

    @Mock
    private SchemaService schemaService;

    @Mock
    private FieldValidator schemaValidator;

    private Schema schema;

    private SchemaDTO schemaRQV1DTO;
    private FieldDTO fieldV1DTO;

    @BeforeEach
    void setup() {
        String name = TestHelper.getRandomId("sch");
        schema = DomainTestHelper.getRandomSchema(name);
        fieldV1DTO = DtoTestHelper.getRandomFieldV1DTO();
        schemaRQV1DTO = DtoTestHelper.getRandomSchemaRequestV1DTO(name, fieldV1DTO);
    }

    @Test
    void createSchemaShouldSaveSchemaAndReturnOK() {

        when(schemaService.save(any())).thenReturn(schema);
        when(schemaValidator.validateFieldValue(any(), any(), any())).thenReturn(true);
        when(schemaValidator.validateConstraints(any(), any(), any())).thenReturn(true);


        var result = underTest.createSchema(schemaRQV1DTO);

        verify(schemaValidator).validateFieldValue(fieldV1DTO.defaultValue(), fieldV1DTO.type(), fieldV1DTO.mode());
        verify(schemaValidator).validateConstraints(fieldV1DTO.constraints(), fieldV1DTO.type(), fieldV1DTO.mode());

        ArgumentCaptor<Schema> captor = ArgumentCaptor.forClass(Schema.class);
        verify(schemaService).save(captor.capture());

        var iSchema = captor.getValue();

        assertThat(iSchema).usingRecursiveComparison().ignoringFields("id").isEqualTo(schemaRQV1DTO);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).usingRecursiveComparison().isEqualTo(schema);
    }

    @Test
    void createSchemaShouldThrowExceptionIfSchemaNameAlreadyExists() {
        when(schemaValidator.validateFieldValue(any(), any(), any())).thenReturn(true);
        when(schemaValidator.validateConstraints(any(), any(), any())).thenReturn(true);
        when(schemaService.findByName(any())).thenReturn(Optional.of(schema));

        assertThatThrownBy(() -> underTest.createSchema(schemaRQV1DTO))
                .isInstanceOf(SchemaNameExistsException.class)
                .extracting("schemaName")
                .isEqualTo(schema.name());

        verify(schemaValidator).validateFieldValue(fieldV1DTO.defaultValue(), fieldV1DTO.type(), fieldV1DTO.mode());
        verify(schemaValidator).validateConstraints(fieldV1DTO.constraints(), fieldV1DTO.type(), fieldV1DTO.mode());
        verify(schemaService).findByName(schemaRQV1DTO.name());
    }

    @Test
    void createSchemaShouldThrowExceptionForInvalidDefaultFieldValue() {
        when(schemaValidator.validateFieldValue(any(), any(), any())).thenReturn(false);

        assertThatThrownBy(() -> underTest.createSchema(schemaRQV1DTO))
                .isInstanceOf(InvalidFieldValueException.class)
                .extracting("type", "mode", "rejectedValue")
                .containsExactly(fieldV1DTO.type(), fieldV1DTO.mode(), fieldV1DTO.defaultValue());

        verify(schemaValidator).validateFieldValue(fieldV1DTO.defaultValue(), fieldV1DTO.type(), fieldV1DTO.mode());
    }

    @Test
    void createSchemaShouldThrowExceptionForInvalidConstraints() {
        when(schemaValidator.validateFieldValue(any(), any(), any())).thenReturn(true);
        when(schemaValidator.validateConstraints(any(), any(), any())).thenReturn(false);

        assertThatThrownBy(() -> underTest.createSchema(schemaRQV1DTO))
                .isInstanceOf(InvalidConstraintsException.class)
                .extracting("type", "mode", "constraints")
                .containsExactly(fieldV1DTO.type(), fieldV1DTO.mode(), fieldV1DTO.constraints());

        verify(schemaValidator).validateFieldValue(fieldV1DTO.defaultValue(), fieldV1DTO.type(), fieldV1DTO.mode());
        verify(schemaValidator).validateConstraints(fieldV1DTO.constraints(), fieldV1DTO.type(), fieldV1DTO.mode());
    }
}
