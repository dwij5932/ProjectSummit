package com.dis.ms.productms.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    @Id
    private String prdId;

    private String name;

    private String sellerId;

    private double price;

    private Double discount;

    private String description;

    private int amount;

    private String imageId;

    private String imageUrl;

    private String altText;
}
