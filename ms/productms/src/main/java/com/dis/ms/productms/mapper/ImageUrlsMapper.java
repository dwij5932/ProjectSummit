package com.dis.ms.productms.mapper;

import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.entity.ImageUrls;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ImageUrlsMapper {

    public ImageUrlsDTO toImageUrlsDTO(ImageUrls imageUrls){
        return ImageUrlsDTO.builder()
                .imageId(imageUrls.getImageId())
                .imageUrl(imageUrls.getImageUrl())
                .altText(imageUrls.getAltText())
                .build();
    }

    public ImageUrls toImageUrls(ImageUrlsDTO imageUrlsDTO){
        return ImageUrls.builder()
                .imageId(imageUrlsDTO.getImageId())
                .imageUrl(imageUrlsDTO.getImageUrl())
                .altText(imageUrlsDTO.getAltText())
                .build();
    }
}
