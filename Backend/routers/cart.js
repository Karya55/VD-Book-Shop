const express = require("express");
const { addProduct, removeProduct, getCart } = require("../controllers/cart");
const { getAccessToRoute } = require("../middlewares/access_route");

const router = express.Router();

router.get("/:cartId", getAccessToRoute, getCart);
router.post("/add-product", getAccessToRoute, addProduct);
router.delete("/remove-product", getAccessToRoute, removeProduct);

module.exports = router;
