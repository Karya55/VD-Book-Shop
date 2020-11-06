const asyncHandler = require("express-async-handler");

const login = asyncHandler(async (req, res, next) => {
    console.log("login");
});

const register = asyncHandler(async (req, res, next) => {
    console.log("register");
});

module.exports = {
    login,
    register
};
