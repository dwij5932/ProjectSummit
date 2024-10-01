package com.dis.ms.productms.service.impl;

import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.entity.ImageUrls;
import com.dis.ms.productms.exception.ResourceNotFoundException;
import com.dis.ms.productms.repository.impl.ImageUriRepositoryImpl;
import com.dis.ms.productms.service.ImageUrlsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageUrlsServiceImpl implements ImageUrlsService {

    private final ImageUriRepositoryImpl imageUriRepository;

    @Override
    public List<ImageUrlsDTO> getAllImagesById(String prdId) {

        log.info("Image for: {}", prdId);
        List<ImageUrlsDTO> imageUrlsDTOList = new ArrayList<>();
        List<ImageUrls> imageUrls = imageUriRepository.findAllById(prdId);
        log.info("Image found : {}", imageUrls.size());
        try{
            for (ImageUrls imageUrls1 : imageUrls ){
                ImageUrlsDTO imageUrlsDTO = ImageUrlsDTO.builder()
                        .imageUrl(imageUrls1.getImageUrl())
                        .altText(imageUrls1.getAltText())
                        .build();
                imageUrlsDTOList.add(imageUrlsDTO);
            }
        }catch (DataAccessException e){
            log.error(e.toString());
        }

        return imageUrlsDTOList;
    }

    @Override
    public ImageUrls getImageById(String imageId) {

        return imageUriRepository.findById(imageId);
    }

    @Override
    public void saveImage(ImageUrlsDTO imageUrlsDTO, String prdId) {

        try{
            ImageUrls imageUrls = ImageUrls.builder()
                    .imageId("s" + UUID.randomUUID().toString().substring(0, 8))
                    .prdId(prdId)
                    .imageUrl(imageUrlsDTO.getImageUrl())
                    .altText(imageUrlsDTO.getAltText())
                    .deleted(false)
                    .build();
            if(imageUriRepository.save(imageUrls) == 1){
                log.info("Save Image: {}", imageUrls.toString());
            }else{
                log.error("Invalided Access");
            }

        }catch (DataAccessException e){
            log.error(e.toString());
        }
    }

    @Override
    public void updateImage(ImageUrlsDTO imageUrlsDTO, String prdId) throws ResourceAccessException {

        try {
            if (imageUriRepository.findById(imageUrlsDTO.getImageId()) != null) {
                ImageUrls imageUrls = ImageUrls.builder()
                        .imageId(imageUrlsDTO.getImageId())
                        .imageUrl(imageUrlsDTO.getImageUrl())
                        .prdId(prdId)
                        .altText(imageUrlsDTO.getAltText())
                        .build();
                if (imageUriRepository.update(imageUrls) == 1) {
                    log.info("Update Product: {}", imageUrls);
                } else {
                    log.error("Product update failed with PrdId: {}", imageUrlsDTO.getImageId());
                }
            } else {
                throw new ResourceNotFoundException("Product " + imageUrlsDTO + " not found.");
            }
        }catch (DataAccessException e){
            log.error(e.toString());
        }
    }

    @Override
    public void deleteImage(String imageId) {

        imageUriRepository.deleteById(imageId);
    }
}
