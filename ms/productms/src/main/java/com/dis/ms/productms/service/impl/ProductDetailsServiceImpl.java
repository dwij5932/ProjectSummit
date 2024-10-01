package com.dis.ms.productms.service.impl;

import com.dis.ms.productms.dto.ImageUrlsDTO;
import com.dis.ms.productms.dto.ProductDetailsDTO;
import com.dis.ms.productms.entity.ProductDetails;
import com.dis.ms.productms.entity.ProductImage;
import com.dis.ms.productms.exception.ResourceNotFoundException;
import com.dis.ms.productms.repository.impl.ProductDetailsRepositoryImpl;
import com.dis.ms.productms.service.ProductDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsServiceImpl implements ProductDetailsService {

    private final ImageUrlsServiceImpl imageUrlsService;
    private final ProductDetailsRepositoryImpl productDetailsRepository;

    @Override
    public List<ProductDetailsDTO> getAllProducts(int offset) {

        try {
            log.info("Get Products with offset: {}", offset);
            List<ProductImage> productDetailsList = productDetailsRepository.findAll(offset);

            Map<String, ProductDetailsDTO> productDetailsDTOMap = new LinkedHashMap<>();

            for (ProductImage productDetails : productDetailsList) {

                String prdId = productDetails.getPrdId();

                productDetailsDTOMap.putIfAbsent(prdId, ProductDetailsDTO.builder()
                        .prdId(productDetails.getPrdId())
                        .name(productDetails.getName())
                        .sellerId(productDetails.getSellerId())
                        .price(productDetails.getPrice())
                        .discount(productDetails.getDiscount())
                        .description(productDetails.getDescription())
                        .amount(productDetails.getAmount())
                        .imageUrlsDTOList(new ArrayList<>())
                        .build());

                productDetailsDTOMap.get(prdId).getImageUrlsDTOList().add(ImageUrlsDTO.builder()
                        .imageId(productDetails.getImageId())
                        .imageUrl(productDetails.getImageUrl())
                        .altText(productDetails.getAltText())
                        .build());
            }

            return new ArrayList<>(productDetailsDTOMap.values());
        }catch (DataAccessException e){
            log.error(e.toString());
        }

        return new ArrayList<>();
    }

    @Override
    public ProductDetailsDTO getProductById(String prdId) {

        try {
            log.info("Get Product with prdId: {}", prdId);
            ProductDetails productDetails = productDetailsRepository.findById(prdId);
            List<ImageUrlsDTO> imageUrlsDTOList = imageUrlsService.getAllImagesById(prdId);

            return ProductDetailsDTO.builder()
                    .prdId(productDetails.getPrdId())
                    .name(productDetails.getName())
                    .sellerId(productDetails.getSellerId())
                    .price(productDetails.getPrice())
                    .discount(productDetails.getDiscount())
                    .description(productDetails.getDescription())
                    .amount(productDetails.getAmount())
                    .imageUrlsDTOList(imageUrlsDTOList)
                    .build();
        }catch (DataAccessException e){
            log.error(e.toString());
        }

        return null;
    }

    @Override
    public void saveProduct(ProductDetailsDTO productDetailsDTO) {

        try {
            ProductDetails productDetails = ProductDetails.builder()
                    .prdId("p" + UUID.randomUUID().toString().substring(0, 8))
                    .name(productDetailsDTO.getName())
                    .sellerId(productDetailsDTO.getSellerId())
                    .price(productDetailsDTO.getPrice())
                    .discount(productDetailsDTO.getDiscount())
                    .description(productDetailsDTO.getDescription())
                    .amount(productDetailsDTO.getAmount())
                    .deleted(false)
                    .build();

            if (productDetailsRepository.save(productDetails) == 1) {
                log.info("Save Product: {}", productDetails);
            } else {
                log.error("Invalided Access");
            }

            for (ImageUrlsDTO imageUrlsDTO : productDetailsDTO.getImageUrlsDTOList()) {
                imageUrlsService.saveImage(imageUrlsDTO, productDetails.getPrdId());
            }
        } catch (DataAccessException e) {
            log.error(e.toString());
        }

    }

    @Override
    public void updateProduct(ProductDetailsDTO productDetailsDTO) throws ResourceAccessException {

        try{
            if (productDetailsRepository.findById(productDetailsDTO.getPrdId()) != null) {
                ProductDetails productDetails = ProductDetails.builder()
                        .prdId("p" + UUID.randomUUID().toString().substring(0, 8))
                        .name(productDetailsDTO.getName())
                        .sellerId(productDetailsDTO.getSellerId())
                        .price(productDetailsDTO.getPrice())
                        .discount(productDetailsDTO.getDiscount())
                        .description(productDetailsDTO.getDescription())
                        .amount(productDetailsDTO.getAmount())
                        .build();

                if(productDetailsRepository.update(productDetails) == 1){
                    log.info("Update Product: {}", productDetails);
                }else{
                    log.error("Product update failed with PrdId: {}", productDetails.getPrdId());
                }

                for (ImageUrlsDTO imageUrlsDTO: productDetailsDTO.getImageUrlsDTOList()){
                    if(imageUrlsDTO.getImageId() != null){
                        imageUrlsService.updateImage(imageUrlsDTO, productDetails.getPrdId());
                    }else{
                        imageUrlsService.saveImage(imageUrlsDTO, productDetails.getPrdId());
                    }
                }
            } else {
                throw new ResourceNotFoundException("Product " + productDetailsDTO + " not found.");
            }
        }catch (DataAccessException e){
            log.error(e.toString());
        }
    }

    @Override
    public void deleteProduct(String prdId) {

        productDetailsRepository.deleteById(prdId);
    }
}
