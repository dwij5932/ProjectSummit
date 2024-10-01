package com.dis.ms.productms.util;

public class DBQueries {

    private DBQueries(){throw new IllegalStateException("Utility class");}

    public static final String GET_ALL_PRODUCTS = "SELECT  pd.prdId, pd.name, pd.sellerId, pd.price, pd.discount, pd.description, pd.amount, iu.imageId, iu.imageUrl,iu.altText FROM (SELECT DISTINCT pd.prdId FROM master_data.product_details pd ORDER BY pd.prdId LIMIT 20 OFFSET ?) pp JOIN master_data.product_details pd ON pp.prdId = pd.prdId LEFT JOIN master_data.image_urls iu ON pd.prdId = iu.prdId ORDER BY pd.prdId;";

    public static final String  GET_PRODUCT_BY_ID = "SELECT * FROM master_data.product_details WHERE prdId = ?";

    public static final String INSERT_A_PRODUCT = "INSERT INTO master_data.product_details(prdId, name, sellerId, price, discount, description, amount) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE_A_PRODUCT = "UPDATE master_data.product_details SET name = ?, sellerId = ?, price = ?, discount = ?, description = ?, amount = ? WHERE prdId = ?";

    public static final String DELETE_A_PRODUCT = "UPDATE master_data.product_details SET deleted = true WHERE prdId = ?";

    public static final String GET_ALL_IMAGES_BY_PRDID = "SELECT * FROM master_data.image_urls WHERE prdId = ?";

    public static final String  GET_IMAGE_BY_ID = "SELECT * FROM master_data.image_urls WHERE imageId = ?";

    public static final String INSERT_A_IMAGE = "INSERT INTO master_data.image_urls (imageId, prdId, imageUrl, altText) VALUES (?, ?, ?, ?)";

    public static final String UPDATE_A_IMAGE = "UPDATE master_data.image_urls SET prdId = ?, imageUrl = ?, altText = ? WHERE imageId = ?";

    public static final String DELETE_A_IMAGE = "UPDATE master_data.image_urls SET deleted = true WHERE imageId = ?";
}
