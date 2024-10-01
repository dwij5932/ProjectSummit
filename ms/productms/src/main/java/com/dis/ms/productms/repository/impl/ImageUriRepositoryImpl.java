package com.dis.ms.productms.repository.impl;

import com.dis.ms.productms.entity.ImageUrls;
import com.dis.ms.productms.mapper.ImageUrlsRowMapper;
import com.dis.ms.productms.repository.ImageUriRepository;
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
public class ImageUriRepositoryImpl implements ImageUriRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<ImageUrls> findAllById(String prdId) throws DataAccessException{
        log.info("Fetch images with prdId : {}", prdId);
        Object[] args = {prdId};
        return jdbcTemplate.query(GET_ALL_IMAGES_BY_PRDID, new ImageUrlsRowMapper(), args);
    }

    @Override
    public ImageUrls findById(String imageId) {
        log.info("Fetch imageId : {}", imageId);
        Object[] args = {imageId};
        return jdbcTemplate.query( GET_IMAGE_BY_ID,new ImageUrlsRowMapper(), args).getFirst();
    }

    @Override
    public int save(ImageUrls image) throws DataAccessException {

        log.info("Add to Database: {}", image.getImageId());
        return jdbcTemplate.update(INSERT_A_IMAGE, image.getImageId(), image.getPrdId(), image.getImageUrl(), image.getAltText());
    }

    @Override
    public int update(ImageUrls image) {

        return jdbcTemplate.update(UPDATE_A_IMAGE, image.getPrdId(), image.getImageUrl(), image.getAltText(), image.getImageId());
    }

    @Override
    public int deleteById(String imageId) {
        return jdbcTemplate.update(DELETE_A_IMAGE, imageId);
    }
}
