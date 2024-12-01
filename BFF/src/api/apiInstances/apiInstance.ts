import axios from "axios";
import config from "../../config.json";

const productServiceInstance = axios.create({
    baseURL: `${config.services.productService}/api/v1/product`,
    withCredentials: false,
    timeout: 1000,
});

const apiInstances = {
    productServiceInstance
  };

export default apiInstances;
