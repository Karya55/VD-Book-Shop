const asyncHandler = require("express-async-handler");
const BookCategory = require("../models/BookCategory");
const Book = require("../models/Book");
const CustomError = require("../helpers/CustomError");

const addCategory = asyncHandler(async (req, res, next) => {
    const { name } = req.body;

    await BookCategory.create({
        name
    });

    res.status(200).json({
        success: true
    });
});

const getCategories = asyncHandler(async (req, res, next) => {
    const categories = await BookCategory.find().select("-__v");

    res.status(200).json({
        success: true,
        data: {
            categories
        }
    });
});

const getBooks = asyncHandler(async (req, res, next) => {
    const categoryId = req.params.categoryId;

    const category = await BookCategory.findById(categoryId);

    if (!category)
        return next(new CustomError("Category not found", 404));

    const books = await Book.find({
        category
    })
    .populate({
        path: 'category',
        select: "_id name"
    })
    .select("-__v");

    res.status(200).json({
        success: true,
        data: {
            books
        }
    });
});

module.exports = {
    addCategory,
    getCategories,
    getBooks
};
