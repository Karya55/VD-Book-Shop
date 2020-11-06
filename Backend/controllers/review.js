const asyncHandler = require("express-async-handler");
const User = require("../models/User");
const Book = require("../models/Book");
const Review = require("../models/Review");
const CustomError = require("../helpers/CustomError");

const addReview = asyncHandler(async (req, res, next) => {
    const { bookId, review, star } = req.body;
    
    const book = await Book.findById(bookId);
    
    if (!book)
        return next(new CustomError("Book not found", 404));

    const createdReview = await Review.create({
        user: req.user.id,
        book: bookId,
        review,
        star
    });

    if (star)
        await Book.findByIdAndUpdate(bookId, {
            $push: {
                reviews: createdReview
            },
            $inc: {
                totalReview: 1,
                totalStar: star
            }
        });
    else
        await Book.findByIdAndUpdate(bookId, {
            $push: {
                reviews: createdReview
            }
        });

    await User.findByIdAndUpdate(req.user.id, {
        $push: {
            reviews: createdReview
        }
    });

    res.status(200).json({
        success: true
    });
});

const getReviews = asyncHandler(async (req, res, next) => {
    const reviews = await Review.find({
        user: req.user.id
    }).select("-__v");

    res.status(200).json({
        success: true,
        data: {
            reviews
        }
    });    
});

module.exports = {
    addReview,
    getReviews
};
