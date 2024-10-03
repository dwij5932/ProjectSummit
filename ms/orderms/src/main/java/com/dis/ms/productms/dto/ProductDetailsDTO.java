package com.dis.ms.productms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Name can not be empty")
    @JsonProperty("name")
    private String name;

    @JsonProperty("sellerId")
    private String sellerId;

    @NotEmpty(message = "Price can not be empty")
    @JsonProperty("price")
    private double price;

    @JsonProperty("discount")
    private Double discount;

    @JsonProperty("description")
    private String description;

    @NotEmpty(message = "Amount can not be empty")
    @JsonProperty("amount")
    private int amount;

    @JsonProperty("images")
    private List<ImageUrlsDTO> imageUrlsDTOList;
}
