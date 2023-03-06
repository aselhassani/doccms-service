package com.doccms.ws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.doccms.adapter.ws.SchemaControllerV1;
import com.doccms.adapter.ws.dto.SchemaRequestV1DTO;
import com.doccms.domain.model.Schema;
import com.doccms.domain.service.SchemaService;
import com.doccms.helpers.DomainTestHelper;
import com.doccms.helpers.DtoTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class SchemaControllerV1Test {

    @InjectMocks
    private SchemaControllerV1 underTest;

    @Mock
    private SchemaService schemaService;

    private Schema schema;

    private SchemaRequestV1DTO schemaResponseV1DTO;

    private SchemaRequestV1DTO schemaRequestV1DTO;

    @BeforeEach
    void setup() {
        schema = DomainTestHelper.getRandomSchema();
        schemaRequestV1DTO = DtoTestHelper.getRandomSchemaRequestV1DTO();
    }

    @Test
    void createSchemaShouldSaveSchemaAndReturnOK() {

        when(schemaService.save(any())).thenReturn(schema);

        var result = underTest.createSchema(schemaRequestV1DTO);

        ArgumentCaptor<Schema> captor = ArgumentCaptor.forClass(Schema.class);

        verify(schemaService).save(captor.capture());

        var iSchema = captor.getValue();

        assertThat(iSchema).usingRecursiveComparison().ignoringFields("id").isEqualTo(schemaRequestV1DTO);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).usingRecursiveComparison().ignoringFields().isEqualTo(schema);
    }
}
