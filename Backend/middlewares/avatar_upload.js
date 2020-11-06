const multer = require('multer');
const path = require('path');
const CustomError = require("../helpers/CustomError");

const storage = multer.diskStorage({
    destination: function(req, file, cb) {
        const rootDir = path.dirname(require.main.filename);
        cb(null, path.join(rootDir, "/public/uploads"));
    },
    filename: function(req, file, cb) {
        const extension = file.mimetype.split("/")[1];
        req.savedAvatar = "avatar_" + req.user.id + "." + extension;
        cb(null, req.savedAvatar);
    }
});

const fileFilter = (req, file, cb) => {
    let allowedMimeTypes = ["image/jpg", "image/gif", "image/jpeg", "image/png"];
    if (!allowedMimeTypes.includes(file.mimetype)) {
        return cb(new CustomError("Please, provide a valid image file", 400), false);
    }
    return cb(null, true);
};

const avatarUpload = multer({
    storage,
    fileFilter
});

module.exports = avatarUpload;
