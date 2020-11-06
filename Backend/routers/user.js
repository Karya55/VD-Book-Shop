const express = require("express");
const { getUser, getAllUsers, addFavorite, getFavorites, getReviews } = require("../controllers/user");
const { getAccessToRoute, adminAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/all", [getAccessToRoute, adminAccessToRoute], getAllUsers);
router.get("/:id/favorites", getAccessToRoute, getFavorites);
router.get("/:id/reviews", getAccessToRoute, getReviews);
router.get("/:id", getAccessToRoute, getUser);
router.get("/add-favorite/:bookId", getAccessToRoute, addFavorite);

module.exports = router;
