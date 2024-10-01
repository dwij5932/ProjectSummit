package com.dis.ms.productms.repository;

import com.dis.ms.productms.entity.ImageUrls;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ImageUriRepository {

    public List<ImageUrls> findAllById(String prdId) throws DataAccessException;

    public ImageUrls findById(String imageId);

    int save(ImageUrls image)  throws DataAccessException;

    int update(ImageUrls image);

    int deleteById(String imageId);
}
