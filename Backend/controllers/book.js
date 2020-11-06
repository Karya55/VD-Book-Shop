const asyncHandler = require("express-async-handler");
const Book = require("../models/Book");
const BookCategory = require("../models/BookCategory");
const CustomError = require("../helpers/CustomError");

const getBook = asyncHandler(async (req, res, next) => {
    const id = req.params.id;

    const book = await Book.findById(id).select("-__v");

    if (!book)
        return next(new CustomError("Book not found", 404));

    const averageStar = book.totalStart / book.totalReview;
    book.totalStart = undefined;
    book.totalReview = undefined;

    res.status(200).json({
        success: true,
        data: {
            book: {
                averageStar: (!averageStar) ? 0 : averageStar,
                ...book.toObject()
            }
        }
    });
});

const getAllBooks = asyncHandler(async (req, res, next) => {
    const books = await Book.find().select("-__v");

    res.status(200).json({
        success: true,
        data: {
            books
        }
    });
});

const addBook = asyncHandler(async (req, res, next) => {
    const { title, author, categoryName, cover, pageCount } = req.body;

    const category = await BookCategory.findOne({
        name: categoryName
    });

    if (!category)
        return next(new CustomError("Category not found", 404));

    await Book.create({
        title,
        author,
        category,
        cover,
        pageCount
    });

    res.status(200).json({
        success: true
    });
});

module.exports = {
    getBook,
    getAllBooks,
    addBook
};
