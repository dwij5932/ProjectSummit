package com.dis.ms.productms.repository;

import com.dis.ms.productms.entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductDetailsRepository extends JpaRepository<ProductDetails, String> {

    Optional<ProductDetails> findById(String prdId);

    @Query("SELECT p FROM ProductDetails p WHERE p.deleted = false")
    Page<ProductDetails> findAllActive(Pageable pageable);

    @Query("SELECT p FROM ProductDetails p WHERE p.prdId = :prdId AND p.deleted = false")
    Optional<ProductDetails> findByIdAndNotDeleted(String prdId);
}
