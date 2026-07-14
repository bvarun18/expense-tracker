import api from "../api/axios";

const getSummary = async () => {
    const response = await api.get("/dashboard/summary");
    return response.data.data;
};

const getRecentExpenses = async () => {
    const response = await api.get("/dashboard/recent-expenses");
    return response.data.data;
};

const getCategorySummary = async () => {
    const response = await api.get("/dashboard/category-summary");
    return response.data.data;
};

const getMonthlySummary = async () => {
    const response = await api.get("/dashboard/monthly-summary");
    return response.data.data;
};

export default {
    getSummary,
    getRecentExpenses,
    getCategorySummary,
    getMonthlySummary
};