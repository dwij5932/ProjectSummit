package com.dis.ms.productms.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageUrls {

    @Id
    private String imageId;

    private String prdId;

    private String imageUrl;

    private String altText;

    private Boolean deleted;
}
