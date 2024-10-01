package com.dis.ms.productms.mapper;

import com.dis.ms.productms.entity.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@RequiredArgsConstructor
public class ProductImageRowMapper implements RowMapper<ProductImage> {

    @Override
    public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {

        return ProductImage.builder()
                .prdId(rs.getString("prdId"))
                .name(rs.getString("name"))
                .sellerId(rs.getString("name"))
                .price(rs.getDouble("price"))
                .discount(rs.getDouble("discount"))
                .description(rs.getString("description"))
                .amount(rs.getInt("amount"))
                .imageId(rs.getString("imageId"))
                .imageUrl(rs.getString("imageUrl"))
                .altText(rs.getString("altText"))
                .build();
    }
}
