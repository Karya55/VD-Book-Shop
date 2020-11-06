const mongoose = require("mongoose");
const bcrypt = require("bcrypt");

const Schema = mongoose.Schema;

const UserSchema = new Schema({
    email: {
        type: String,
        required: [true, "Missing field: e-mail"],
        unique: true,
        match: [
            /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/,
            "Validation failed: E-mail address is not in correct form"
        ]
    },
    password: {
        type: String,
        required: [true, "Missing field: password"],
        minlength: [6, "Validation failed: Minimum password length is 6"],
        select: false
    },
    name: {
        type: String,
        required: [true, "Missing field: name"]
    },
    role: {
        type: String,
        default: "user",
        enum: [
            "user",
            "admin"
        ]
    },
    avatar: {
        type: String,
        default: "default.jpg"
    }
});

UserSchema.pre("save", function(next) {
    if (!this.isModified("password"))
        next();

    bcrypt.genSalt(10, (err, salt) => {
        if (err) next(err);
        bcrypt.hash(this.password, salt, (err, hash) => {
            if (err) next(err);
            this.password = hash;
            next();
        });
    });
});

module.exports = mongoose.model("user", UserSchema);
