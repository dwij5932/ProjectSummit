package com.dis.ms.productms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Image Url can not be empty")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("altText")
    private String altText;
}
