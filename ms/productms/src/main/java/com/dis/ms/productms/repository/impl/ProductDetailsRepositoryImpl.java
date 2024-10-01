package com.dis.ms.productms.repository.impl;

import com.dis.ms.productms.entity.ProductDetails;
import com.dis.ms.productms.entity.ProductImage;
import com.dis.ms.productms.mapper.ProductDetailsRowMapper;
import com.dis.ms.productms.mapper.ProductImageRowMapper;
import com.dis.ms.productms.repository.ProductDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

import static com.dis.ms.productms.util.DBQueries.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsRepositoryImpl implements ProductDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ProductImage> findAll(int offset) {
        log.info("Fetch product with offset: {}", offset);
        Object[] args = {offset};
        return jdbcTemplate.query(GET_ALL_PRODUCTS, new ProductImageRowMapper(), args);
    }

    @Override
    public ProductDetails findById(String prdId) {
        log.info("Fetch prdId: {}", prdId);
        Object[] args = {prdId};
        return jdbcTemplate.query(GET_PRODUCT_BY_ID, new ProductDetailsRowMapper(), args).getFirst();
    }

    @Override
    public int save(ProductDetails product) throws DataAccessException {
        log.info("Add to Database: {}", product.getPrdId());
        return jdbcTemplate.update(INSERT_A_PRODUCT,
                product.getPrdId(),product.getName(), product.getSellerId(), product.getPrice(),
                product.getDiscount(), product.getDescription(), product.getAmount());
    }

    @Override
    public int update(ProductDetails product) {
        return jdbcTemplate.update(UPDATE_A_PRODUCT,
                product.getName(), product.getSellerId(), product.getPrice(),
                product.getDiscount(), product.getDescription(), product.getAmount(), product.getPrdId());
    }

    @Override
    public int deleteById(String prdId) {
        return jdbcTemplate.update(DELETE_A_PRODUCT, prdId);
    }
}
