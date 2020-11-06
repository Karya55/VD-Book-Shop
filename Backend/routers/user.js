const express = require("express");
const { getUser, getAllUsers } = require("../controllers/user");
const { getAccessToRoute, adminAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/all", adminAccessToRoute, getAllUsers);
router.get("/:id", getAccessToRoute, getUser);

module.exports = router;
