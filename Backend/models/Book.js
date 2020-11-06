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
    }
});

module.exports = mongoose.model("book", BookSchema);
