const asyncHandler = require("express-async-handler");
const User = require("../models/User");
const CustomError = require("../helpers/CustomError");
const bcrypt = require("bcrypt");
const { sendJWT } = require("../helpers/token");

const login = asyncHandler(async (req, res, next) => {
    const { email, password } = req.body;

    if (!(email && password))
        return next(new CustomError("Missing input(s): email, password", 400));

    const user = await User.findOne({ email }).select("+password");

    if (!user)
        return next(new CustomError("User not found", 404));

    if (!bcrypt.compareSync(password, user.password))
        return next(new CustomError("Check credentials", 400));

    sendJWT(user, res);
});

const register = asyncHandler(async (req, res, next) => {
    const { email, password, username } = req.body;

    const user = await User.create({
       email,
       password,
       name: username 
    });

    sendJWT(user, res);
});

module.exports = {
    login,
    register
};
