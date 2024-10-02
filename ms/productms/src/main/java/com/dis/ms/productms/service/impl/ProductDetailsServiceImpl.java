package com.dis.ms.productms.service.impl;


import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.dto.ProductDetailsDTO;
import com.dis.ms.productms.entity.ProductDetails;
import com.dis.ms.productms.exception.ResourceNotFoundException;
import com.dis.ms.productms.mapper.ProductDetailsMapper;
import com.dis.ms.productms.repository.ProductDetailsRepository;
import com.dis.ms.productms.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ProductDetailsRepository productDetailsRepository;
    private final ProductDetailsMapper productDetailsMapper;
    private final ImageUrlsServiceImpl imageUrlsService;

    @Override
    public List<ProductDetailsDTO> getAllProducts(int offset, int limit) {

        log.info("Fetching products with offset: {}", offset);
        PageRequest pageRequest = PageRequest.of(offset, limit);
        List<ProductDetails> products = productDetailsRepository.findAllActive(pageRequest).getContent();

        return products.stream().map(product -> {
            List<ImageUrlsDTO> images = imageUrlsService.getAllImagesById(product.getPrdId());

            ProductDetailsDTO productDetailsDTO = productDetailsMapper.toProductDetailsDTO(product);
            productDetailsDTO.setImageUrlsDTOList(images);

            return productDetailsDTO;
        }).toList();
    }

    @Override
    public ProductDetailsDTO getProductById(String prdId) {
        Optional<ProductDetails> optionalProduct = productDetailsRepository.findByIdAndNotDeleted(prdId);

        if (optionalProduct.isPresent()) {
            ProductDetails product = optionalProduct.get();
            List<ImageUrlsDTO> images = imageUrlsService.getAllImagesById(product.getPrdId());
            ProductDetailsDTO productDetailsDTO = productDetailsMapper.toProductDetailsDTO(product);
            productDetailsDTO.setImageUrlsDTOList(images);
            return productDetailsDTO;
        } else {
            throw new ResourceNotFoundException("Product with id " + prdId + " not found or is deleted.");
        }
    }

    @Override
    public void saveProduct(ProductDetailsDTO productDetailsDTO) {
        try {
            String prdId = "p" + UUID.randomUUID().toString().substring(0, 8);
            ProductDetails productDetails = productDetailsMapper.toProductDetails(productDetailsDTO);
            productDetails.setDeleted(false);
            productDetails.setPrdId(prdId);

            productDetailsRepository.save(productDetails);
            log.info("Saved Product: {}", productDetails);

            productDetailsDTO.getImageUrlsDTOList().forEach(image -> imageUrlsService.saveImage(image, productDetails.getPrdId()));

        } catch (DataAccessException e) {
            log.error("Error saving product: {}", e.getMessage());
        }
    }

    @Override
    public void updateProduct(ProductDetailsDTO productDetailsDTO) {
        try {
            productDetailsRepository.save(productDetailsMapper.toProductDetails(productDetailsDTO));

            for (ImageUrlsDTO image : productDetailsDTO.getImageUrlsDTOList()) {
                if(image.getImageId() == null) {
                    imageUrlsService.saveImage(image, productDetailsDTO.getPrdId());
                }else {
                    imageUrlsService.updateImage(image, productDetailsDTO.getPrdId());
                }
            }
        } catch (DataAccessException e) {
            log.error("Error updating product: {}", e.getMessage());
        }
    }

    @Override
    public void deleteProduct(String prdId) {
        Optional<ProductDetails> optionalProduct = productDetailsRepository.findById(prdId);

        if (optionalProduct.isPresent()) {
            ProductDetails product = optionalProduct.get();
            product.setDeleted(true);
            productDetailsRepository.save(product);

            imageUrlsService.deleteImages(prdId);
        } else {
            throw new ResourceNotFoundException("Product with id " + prdId + " not found.");
        }
    }
}
