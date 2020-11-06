const express = require("express");
const { addCategory, getCategories, getBooks } = require("../controllers/book_category");
const { getAccessToRoute, adminAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/", getAccessToRoute, getCategories);
router.post("/add", [getAccessToRoute], addCategory);
router.get("/:categoryId", getAccessToRoute, getBooks);

module.exports = router;
