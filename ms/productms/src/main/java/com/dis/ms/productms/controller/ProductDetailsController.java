package com.dis.ms.productms.controller;

import com.dis.ms.productms.dto.ProductDetailsDTO;
import com.dis.ms.productms.entity.ProductDetails;
import com.dis.ms.productms.exception.ResourceNotFoundException;
import com.dis.ms.productms.service.impl.ProductDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductDetailsController {

    private final ProductDetailsServiceImpl productDetailsService;

    @GetMapping("/all/{offset}")
    public List<ProductDetailsDTO> getAllProducts(@PathVariable("offset") int offset){
        log.info("Get All Product with offset: {}", offset);
        return productDetailsService.getAllProducts(offset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDTO> getProductById(@PathVariable("id") String prdId){

        log.info("Get: {}", prdId);
        ProductDetailsDTO product = productDetailsService.getProductById(prdId);
        log.info(product.toString());
        return product !=null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public void createProduct(@RequestBody ProductDetailsDTO productDetailsDTO) {

        log.info("Post: {}", productDetailsDTO.toString());
        productDetailsService.saveProduct(productDetailsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") String prdId, @RequestBody ProductDetailsDTO productDetailsDTO) {

        try{
            log.info("Id: {}", prdId);
            productDetailsDTO.setPrdId(prdId);
            productDetailsService.updateProduct(productDetailsDTO);
            log.info("Updated: {}", getProductById(prdId).toString());
            return ResponseEntity.ok().build();
        }catch (ResourceNotFoundException exception){
            log.error("Not found: {}", prdId);
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String prdId) {

        log.info("Deleted: {}", prdId);
        productDetailsService.deleteProduct(prdId);
        return ResponseEntity.noContent().build();
    }
}
