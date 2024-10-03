package com.dis.ms.productms.repository;

import com.dis.ms.productms.entity.ImageUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageUrlRepository extends JpaRepository<ImageUrls, String> {

    @Query("SELECT i FROM ImageUrls i WHERE i.prdId = :prdId AND i.deleted = false")
    List<ImageUrls> findAllActiveByProductId(String prdId);
}
