import axios from "axios";


const protocol = 'http'
const hostName = `localhost`
const port = 8081;

const BaseURL = `${protocol}://${hostName}:${port}/api`
const axiosClient = axios.create({
    baseURL: BaseURL,
    timeout: 80000,
})


export {axiosClient};

export default BaseURL;