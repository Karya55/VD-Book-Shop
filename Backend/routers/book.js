const express = require("express");
const { getBook, getAllBooks, addBook, addReview } = require("../controllers/book");
const { getAccessToRoute, adminAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/all", getAccessToRoute, getAllBooks);
router.post("/add", [getAccessToRoute], addBook);
router.get("/:id", getAccessToRoute, getBook);

module.exports = router;
