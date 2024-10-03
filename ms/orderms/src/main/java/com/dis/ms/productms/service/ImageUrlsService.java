package com.dis.ms.productms.service;

import com.dis.ms.productms.dto.ImageUrlsDTO;

import java.util.List;

public interface ImageUrlsService {

    List<ImageUrlsDTO> getAllImagesById(String prdId);

    void saveImage(ImageUrlsDTO imageUrlsDTO, String prdId);

    void updateImage(ImageUrlsDTO imageUrlsDTO, String prdId);

    void deleteImages(String prdId);

}
