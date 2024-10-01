package com.dis.ms.productms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrlsDTO {

    @JsonProperty("imageId")
    private String imageId;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("altText")
    private String altText;
}
