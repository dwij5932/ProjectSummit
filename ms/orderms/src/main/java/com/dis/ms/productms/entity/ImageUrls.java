package com.dis.ms.productms.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ImageUrls {

    @Id
    @Column(name = "imageid", nullable = false)
    private String imageId;

    @Column(name = "prdid", nullable = false)
    private String prdId;

    @Column(name = "imageurl", nullable = false)
    private String imageUrl;

    @Column(name = "alttext")
    private String altText;

    private Boolean deleted;
}
