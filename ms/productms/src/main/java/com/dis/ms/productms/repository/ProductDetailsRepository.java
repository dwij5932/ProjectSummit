package com.dis.ms.productms.repository;

import com.dis.ms.productms.entity.ProductDetails;
import com.dis.ms.productms.entity.ProductImage;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ProductDetailsRepository {

    List<ProductImage> findAll(int offset);

    ProductDetails findById(String prdId);

    int save(ProductDetails product) throws DataAccessException;

    int update(ProductDetails product);

    int deleteById(String prdId);
}
