const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const BookCategorySchema = new Schema({
    name: {
        type: String,
        required: [true, "Missing field: name"],
        unique: true,
    }
});

module.exports = mongoose.model("bookCategory", BookCategorySchema);
