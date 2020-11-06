const express = require("express");
const { addReview, getReviews } = require("../controllers/review");
const { getAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/user-reviews", getAccessToRoute, getReviews);
router.post("/add", getAccessToRoute, addReview);

module.exports = router;
