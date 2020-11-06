const asyncHandler = require("express-async-handler");
const User = require("../models/User");
const Book = require("../models/Book");
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

const addFavorite = asyncHandler(async (req, res, next) => {
    const bookId = req.params.bookId;

    const book = await Book.findById(bookId);
    if (!book)
        return next(new CustomError("Book not found", 404));

    await User.findByIdAndUpdate(req.user.id, {
        $push: {
            favorites: book
        }
    });

    res.status(200).json({
        success: true
    });
});

const getReviews = asyncHandler(async (req, res, next) => {
    const userId = req.params.id;

    const reviews = await User.findById(userId).select("reviews").populate({
        path: "reviews",
        select: "-_id -user -__v",
        populate: {
            path: "book",
            select: "cover author title"
        }
    });

    res.status(200).json({
        success: true,
        data: {
            reviews
        }
    });
});

const getFavorites = asyncHandler(async (req, res, next) => {
    const userId = req.params.id;

    const favorites = await User.findById(userId).select("favorites").populate({
        path: "favorites",
        select: "cover title id author"
    });

    res.status(200).json({
        success: true,
        data: {
            favorites
        }
    });
});

const updateAvatar = asyncHandler(async (req, res, next) => {
    await User.findByIdAndUpdate(req.user.id, {
        avatar: req.savedAvatar
    });

    res.status(200).json({
        success: true,
        data: {
            avatar: req.savedAvatar
        }
    });
});

module.exports = {
    getUser,
    getAllUsers,
    addFavorite,
    getReviews,
    getFavorites,
    updateAvatar
};
