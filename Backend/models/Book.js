const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const BookSchema = new Schema({
    title: {
        type: String,
        required: [true, "Missing field: title"],
        unique: true,
    },
    author: {
        type: String,
        required: [true, "Missing field: author"]
    },
    category: [{
        type: Schema.Types.ObjectId,
        ref: "bookCategory",
    }],
    cover: {
        type: String,
        default: "default.jpg"
    },
    pageCount: {
        type: Number,
        default: 0,
        validate: {
            validator: function(val) {
                return val >= 0
            },
            message: "Invalid page count"
        }
    },
    reviews: [{
        type: Schema.Types.ObjectId,
        ref: "review"
    }],
    totalReview: {
        type: Number,
        default: 0
    },
    totalStar: {
        type: Number,
        default: 0
    }
});

module.exports = mongoose.model("book", BookSchema);
