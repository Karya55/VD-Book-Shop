const express = require("express");
const { getUser, getAllUsers, addFavorite, getFavorites, getReviews, updateAvatar } = require("../controllers/user");
const { getAccessToRoute, adminAccessToRoute } = require("../middlewares/access_route");
const avatarUpload = require("../middlewares/avatar_upload");

const router = express.Router();

router.get("/all", [getAccessToRoute, adminAccessToRoute], getAllUsers);
router.get("/:id/favorites", getAccessToRoute, getFavorites);
router.get("/:id/reviews", getAccessToRoute, getReviews);
router.get("/:id", getAccessToRoute, getUser);
router.get("/add-favorite/:bookId", getAccessToRoute, addFavorite);
router.put("/update-avatar", [getAccessToRoute, avatarUpload.single("avatar")], updateAvatar);

module.exports = router;
