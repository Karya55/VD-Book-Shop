const mongoose = require("mongoose");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

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
    },
    reviews: [{
        type: Schema.Types.ObjectId,
        ref: "review"
    }],
    favorites: [{
        type: Schema.Types.ObjectId,
        ref: "book"
    }]
});

UserSchema.methods.generateJWT = function() {
    const { JWT_SECRET_KEY, JWT_EXPIRE } = process.env;

    const payload = {
        id: this._id,
        email: this.email,
        role: this.role
    };

    const token = jwt.sign(payload, JWT_SECRET_KEY, {
        expiresIn: JWT_EXPIRE
    });

    return token;
}

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
