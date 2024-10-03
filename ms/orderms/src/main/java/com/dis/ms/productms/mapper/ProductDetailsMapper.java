package com.dis.ms.productms.mapper;

import com.dis.ms.productms.dto.ProductDetailsDTO;
import com.dis.ms.productms.entity.ProductDetails;
import org.mapstruct.Mapper;

import java.util.ArrayList;

@Mapper(componentModel = "spring")
public class ProductDetailsMapper {

    public ProductDetailsDTO toProductDetailsDTO(ProductDetails productDetails){
        return ProductDetailsDTO.builder()
                .prdId(productDetails.getPrdId())
                .name(productDetails.getName())
                .sellerId(productDetails.getSellerId())
                .price(productDetails.getPrice())
                .discount(productDetails.getDiscount())
                .description(productDetails.getDescription())
                .amount(productDetails.getAmount())
                .imageUrlsDTOList(new ArrayList<>())
                .build();
    }

    public ProductDetails toProductDetails(ProductDetailsDTO productDetailsDTO){
        return ProductDetails.builder()
                .prdId(productDetailsDTO.getPrdId())
                .name(productDetailsDTO.getName())
                .sellerId(productDetailsDTO.getSellerId())
                .price(productDetailsDTO.getPrice())
                .discount(productDetailsDTO.getDiscount())
                .description(productDetailsDTO.getDescription())
                .amount(productDetailsDTO.getAmount())
                .build();
    }

}
