const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const CartSchema = new Schema({
    products: [{
        type: Schema.Types.ObjectId,
        ref: "book"
    }]
});

module.exports = mongoose.model("cart", CartSchema);
