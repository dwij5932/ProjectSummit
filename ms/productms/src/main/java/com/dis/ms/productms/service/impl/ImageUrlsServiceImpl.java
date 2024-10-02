package com.dis.ms.productms.service.impl;

import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.entity.ImageUrls;
import com.dis.ms.productms.mapper.ImageUrlsMapper;
import com.dis.ms.productms.repository.ImageUrlRepository;
import com.dis.ms.productms.service.ImageUrlsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageUrlsServiceImpl implements ImageUrlsService {

    private final ImageUrlRepository imageUrlRepository;
    private final ImageUrlsMapper imageUrlsMapper;

    @Override
    public List<ImageUrlsDTO> getAllImagesById(String prdId) {

        log.info("Image for: {}", prdId);
        List<ImageUrls> imageUrls = imageUrlRepository.findAllActiveByProductId(prdId);

        return imageUrls.stream()
                .map(imageUrlsMapper::toImageUrlsDTO)
                .toList();
    }

    @Override
    public void saveImage(ImageUrlsDTO imageUrlsDTO, String prdId) {

        String imageId = "i" + UUID.randomUUID().toString().substring(0, 8);
        ImageUrls image = imageUrlsMapper.toImageUrls(imageUrlsDTO);
        image.setPrdId(prdId);
        image.setImageId(imageId);
        imageUrlRepository.save(image);
    }

    @Override
    public void updateImage(ImageUrlsDTO imageUrlsDTO, String prdId) {

        ImageUrls image = imageUrlsMapper.toImageUrls(imageUrlsDTO);
        image.setPrdId(prdId);
        imageUrlRepository.save(image);
    }

    @Override
    public void deleteImages(String prdId) {

        List<ImageUrls> imageUrls = imageUrlRepository.findAllActiveByProductId(prdId);

        for (ImageUrls image : imageUrls) {
            image.setDeleted(true);
            imageUrlRepository.save(image);
        }

        log.info("Product with id {} and its associated images are soft deleted.", prdId);
    }
}
