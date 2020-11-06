const asyncHandler = require("express-async-handler");
const User = require("../models/User");
const CustomError = require("../helpers/CustomError");

const getUser = asyncHandler(async (req, res, next) => {
    const id = req.params.id;

    const user = await User.findById(id).select("-role -__v");

    if (!user)
        return next(new CustomError("User not found", 404));

    res.status(200).json({
        success: true,
        data: {
            user
        }
    });
});

const getAllUsers = asyncHandler(async (req, res, next) => {
    const users = await User.find().select("-role -__v");

    res.status(200).json({
        success: true,
        data: {
            users
        }
    });
});

module.exports = {
    getUser,
    getAllUsers
};
