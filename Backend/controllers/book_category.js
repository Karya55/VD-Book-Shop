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

    let query = Book.find({
        category
    })
    .populate({
        path: 'category',
        select: "_id name"
    })
    .select("-__v -totalStar -totalReview");

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

module.exports = {
    addCategory,
    getCategories,
    getBooks
};
