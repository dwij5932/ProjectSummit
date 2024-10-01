package com.dis.ms.productms.mapper;

import com.dis.ms.productms.entity.ImageUrls;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageUrlsRowMapper implements RowMapper<ImageUrls> {

    @Override
    public ImageUrls mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ImageUrls.builder()
                .imageId(rs.getString("imageId"))
                .imageUrl(rs.getString("imageUrl"))
                .altText(rs.getString("altText"))
                .build();
    }
}
