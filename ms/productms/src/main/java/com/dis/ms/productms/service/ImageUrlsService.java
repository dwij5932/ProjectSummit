package com.dis.ms.productms.service;

import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.entity.ImageUrls;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface ImageUrlsService {

    public List<ImageUrlsDTO> getAllImagesById(String prdId);

    public ImageUrls getImageById(String imageId);

    public void saveImage(ImageUrlsDTO imageUrlsDTO, String prdId);

    public void updateImage(ImageUrlsDTO imageUrlsDTO, String prdId) throws ResourceAccessException;

    public void deleteImage(String imageId);

}
