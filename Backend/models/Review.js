const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const ReviewSchema = new Schema({
    user: {
        type: Schema.Types.ObjectId,
        ref: "user",
        required: [true, "Missing field: user"]
    },
    book: {
        type: Schema.Types.ObjectId,
        ref: "book",
        required: [true, "Missing field: book"]
    },
    review: {
        type: String,
    },
    star: {
        type: Number
    }
});

module.exports = mongoose.model("review", ReviewSchema);
