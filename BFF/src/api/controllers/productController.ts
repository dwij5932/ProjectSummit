import { HttpStatusCode } from "axios";
import productService from "../services/productService";
import { Request, Response } from "express";
import logger from "../../config/logger";
import sharedResponses from "../../shared/sharedResponses";

const getAllProducts = async (req: Request, res: Response) => {
    const { query } = req.params;

    try {
        const fetchProducts = await productService.getAllProducts(query);

        const [ products ] = await Promise.all([
            fetchProducts
        ]);

        const response = {
            products: products
        };

        res.status(HttpStatusCode.Ok).send(response);
    } catch (error) {
        logger.error(
            `Error fetching all products with params: ${query}`,
            error
        )
        return sharedResponses.ErrorResponse(
            res,
            error?.response?.status,
            `Error fetching all products with params: ${query}`,
            error?.response?.data?.data?.message || error.message
        )
    }
};

const getProductById = async (req: Request, res: Response) => {
    const { prdId } = req.params;

    try {
        const fetchProducts = await productService.getProductById(prdId);

        const [ product ] = await Promise.all([
            fetchProducts
        ]);

        const response = {
            product: product
        };

        res.status(HttpStatusCode.Ok).send(response);
    } catch (error) {
        logger.error(
            `Error fetching product with prdId: ${prdId}`,
            error
        )
        return sharedResponses.ErrorResponse(
            res,
            error?.response?.status,
            `Error fetching product with prdId: ${prdId}`,
            error?.response?.data?.data?.message || error.message
        )
    }
};

const productController = {
    getAllProducts,
    getProductById,
};

export default productController;