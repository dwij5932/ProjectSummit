package com.dis.ms.productms.controller;

import com.dis.ms.productms.dto.ProductDetailsDTO;
import com.dis.ms.productms.service.impl.ProductDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsController extends AbstractController<ProductDetailsDTO, String> {

    private final ProductDetailsServiceImpl productDetailsService;

    @Override
    protected List<ProductDetailsDTO> getAll(int offset, int limit) {
        log.info("Get All Product with offset: {}", offset);
        return productDetailsService.getAllProducts(offset, limit);
    }

    @Override
    protected ProductDetailsDTO getById(String prdId) {
        log.info("Get Product by ID: {}", prdId);
        return productDetailsService.getProductById(prdId);
    }

    @Override
    protected void create(ProductDetailsDTO productDetailsDTO) {
        log.info("Creating Product: {}", productDetailsDTO.toString());
        productDetailsService.saveProduct(productDetailsDTO);
    }

    @Override
    protected void update(String prdId, ProductDetailsDTO productDetailsDTO) {
        productDetailsDTO.setPrdId(prdId);
        productDetailsService.updateProduct(productDetailsDTO);
    }

    @Override
    protected void delete(String prdId) {
        log.info("Deleting Product: {}", prdId);
        productDetailsService.deleteProduct(prdId);
    }
}
