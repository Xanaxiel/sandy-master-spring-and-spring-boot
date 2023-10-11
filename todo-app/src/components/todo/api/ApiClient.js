import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://backend-spring-boot.eba-aqkwafe2.us-east-2.elasticbeanstalk.com',
        withCredentials: false
    }
);
