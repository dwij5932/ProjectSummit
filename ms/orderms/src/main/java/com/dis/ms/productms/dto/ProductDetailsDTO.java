package com.dis.ms.productms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDTO {

    @JsonProperty("prdId")
    private String prdId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("sellerId")
    private String sellerId;

    @JsonProperty("price")
    private double price;

    @JsonProperty("discount")
    private Double discount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("images")
    private List<ImageUrlsDTO> imageUrlsDTOList;
}
