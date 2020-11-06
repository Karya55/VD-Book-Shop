const asyncHandler = require("express-async-handler");
const CustomError = require("../helpers/CustomError");
const Book = require("../models/Book");
const Cart = require("../models/Cart");

const addProduct = asyncHandler(async (req, res, next) => {
    const { productId, cartId } = req.body;

    console.log(productId);

    const book = await Book.findById(productId);
    if (!book)
        return next(new CustomError("Book not found", 404));

    await Cart.findByIdAndUpdate(cartId, {
        $push: {
            products: book
        }
    });

    res.status(200).json({
        success: true
    });
});

const removeProduct = asyncHandler(async (req, res, next) => {
    const { productId, cartId } = req.body;

    const book = await Book.findById(productId);
    if (!book)
        return next(new CustomError("Book not found", 404));

    await Cart.findByIdAndUpdate(cartId, {
        $pull: {
            products: productId
        }
    });

    res.status(200).json({
        success: true
    });
});

const getCart = asyncHandler(async (req, res, next) => {
    const cartId = req.params.cartId;

    const cart = await Cart.findById(cartId).select("-_id -__v").populate({
        path: "products",
        select: "cover author title"
    });
    if (!cart)
        return next(new CustomError("Cart not found", 404));

    res.status(200).json({
        success: true,
        data: {
            cart
        }
    });
});

module.exports = {
    addProduct,
    removeProduct,
    getCart
};
