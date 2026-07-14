import api from "../api/axios";

const login = async (email, password) => {
    const response = await api.post("/users/login", {
        email,
        password,
    });

    return response.data;
};

const register = async (user) => {
    const response = await api.post("/users/register", user);
    return response.data;
};

export default {
    login,
    register,
};