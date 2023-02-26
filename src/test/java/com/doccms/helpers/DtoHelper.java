package com.doccms.helpers;


import static com.doccms.helpers.TestHelper.getRandomText;

import com.doccms.ws.dto.DocumentRequestV1DTO;

public class DtoHelper {

    public static DocumentRequestV1DTO randomPostRequestV1Dto() {
        return DocumentRequestV1DTO.builder()
                                   .title("title " + getRandomText(10))
                                   .description("description" + getRandomText(100))
                                   .content("content" + getRandomText(100))
                                   .build();
    }

}
