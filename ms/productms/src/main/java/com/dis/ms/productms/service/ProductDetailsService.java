package com.dis.ms.productms.service;

import com.dis.ms.productms.dto.ProductDetailsDTO;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

public interface ProductDetailsService {

    public List<ProductDetailsDTO> getAllProducts(int offset);

    public ProductDetailsDTO getProductById(String prdId);

    public void saveProduct(ProductDetailsDTO productDetails);

    public void updateProduct(ProductDetailsDTO productDetailsDTO) throws ResourceAccessException;

    public void deleteProduct(String prdId);
}
