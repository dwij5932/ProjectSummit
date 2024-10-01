package com.dis.ms.productms.mapper;

import com.dis.ms.productms.entity.ProductDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ProductDetailsRowMapper implements RowMapper<ProductDetails> {

    @Override
    public ProductDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

        return ProductDetails.builder()
                .prdId(rs.getString("prdId"))
                .name(rs.getString("name"))
                .sellerId(rs.getString("sellerId"))
                .price(rs.getDouble("price"))
                .discount(rs.getDouble("discount"))
                .description(rs.getString("description"))
                .amount(rs.getInt("amount"))
                .build();
    }
}
