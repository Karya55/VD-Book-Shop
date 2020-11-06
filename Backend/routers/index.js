const express = require("express");
const auth = require("./auth");
const user = require("./user");
const book = require("./book");
const bookCategory = require("./book_category");
const review = require("./review");

const router = express.Router();

router.use("/auth", auth);
router.use("/user", user);
router.use("/book", book);
router.use("/book-category", bookCategory);
router.use("/review", review);

module.exports = router;
