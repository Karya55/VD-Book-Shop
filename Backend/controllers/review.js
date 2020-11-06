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

    const user = await User.findById(req.user.id);

    const createdReview = Review.create({
        user,
        book,
        review,
        star
    });

    book.reviews.push(createdReview);
    user.reviews.push(createdReview);

    await book.update();
    await user.update();

    res.status(200).json({
        success: true
    });
});

const getReviews = asyncHandler(async (req, res, next) => {
    const reviews = Review.find({
        user: req.user.id
    });

    res.status(200).json({
        success: true,
        data: {
            reviews
        }
    });
});

const getReviewsOfABook = asyncHandler(async (req, res, next) => {
    const bookId = req.body.bookId;

    const book = await Book.findById(bookId);
    if (!book)
        return next(new CustomError("Book not found", 404));

    const reviews = await Review.find({
        book
    });

    res.status(200).json({
        success: true,
        data: {
            reviews
        }
    });
});

module.exports = {
    addReview,
    getReviews,
    getReviewsOfABook
};
