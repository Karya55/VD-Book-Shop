const express = require("express");
const auth = require("./auth");
const user = require("./user");
const book = require("./book");

const router = express.Router();

router.use("/auth", auth);
router.use("/user", user);
router.use("/book", book);

module.exports = router;
