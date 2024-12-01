import express from "express";
import productController from "../controllers/productController";

const router = express.Router();

router.get("/products/all", productController.getAllProducts);

router.get("/products/:prdId", productController.getProductById);

const producstRoute = {
    router,
}

export default producstRoute;