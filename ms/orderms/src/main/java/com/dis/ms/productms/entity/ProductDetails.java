package com.dis.ms.productms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_details", schema = "master_data")
public class ProductDetails {

    @Id
    @Column(name = "prdid", nullable = false)
    private String prdId;

    private String name;

    @Column(name = "sellerid", nullable = false)
    private String sellerId;

    private double price;

    private Double discount;

    private String description;

    private int amount;

    private Boolean deleted;
}
