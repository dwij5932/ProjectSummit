import logger from "../../config/logger";
import apiInstances from "../apiInstances/apiInstance";
import constants from "../constants/constants";

const getAllProducts = async (query: any) => {

    return await apiInstances.productServiceInstance
    .request({
        url: "/all",
        method: constants.HTTP_METHODS.GET,
        params: {
            offset: query?.offset,
            limit: query?.limit
        }
    })
    .then((response) =>{
        return response.data;
    })
    .catch((error) =>{
        logger.error(
            `An Error occured getting products for: ${query}`
        );
        throw error;
    });
};

const getProductById = async (prdId: any) => {
    return await apiInstances.productServiceInstance
    .request({
        url: `/${prdId}`,
        method: constants.HTTP_METHODS.GET,
    })
    .then((response) =>{
        return response.data;
    })
    .catch((error) =>{
        logger.error(
            `An Error occured getting product with Id: ${prdId}`
        );
        throw error;
    });
};

const productService = {
    getAllProducts,
    getProductById,
};

export default productService;
