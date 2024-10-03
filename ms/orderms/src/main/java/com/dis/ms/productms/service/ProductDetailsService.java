package com.dis.ms.productms.service;

import com.dis.ms.productms.dto.ProductDetailsDTO;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface ProductDetailsService {

    List<ProductDetailsDTO> getAllProducts(int offset, int limit);

    ProductDetailsDTO getProductById(String prdId);

    void saveProduct(ProductDetailsDTO productDetails);

    void updateProduct(ProductDetailsDTO productDetailsDTO) throws ResourceAccessException;

    void deleteProduct(String prdId);
}
