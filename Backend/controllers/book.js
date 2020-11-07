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
    let query = Book.find().select("-__v");

    const sortKey = req.query.sortBy;
    if (sortKey === "most-reviewed")
        query = query.sort("-totalReview");
    else if (sortKey === "highest-price")
        query = query.sort("-price");
    else if (sortKey === "lowest-price")
        query = query.sort("price");

    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 5;
    const startIdx = (page - 1) * limit;

    query.skip(startIdx).limit(limit);

    const books = await query;

    res.status(200).json({
        success: true,
        data: {
            books
        }
    });
});

const addBook = asyncHandler(async (req, res, next) => {
    const { title, author, categoryName, cover, pageCount, description } = req.body;

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
        pageCount,
        description
    });

    res.status(200).json({
        success: true
    });
});

const searchBook = asyncHandler(async (req, res, next) => {
    const title = req.query.title;

    const books = await Book.find({
        title: { $regex: title, $options: 'i' }
    });

    res.status(200).json({
        success: true,
        data: {
            books
        }
    });
});

module.exports = {
    getBook,
    getAllBooks,
    addBook,
    searchBook
};
