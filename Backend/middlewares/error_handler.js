const CustomError = require("../helpers/CustomError");

const customErrorHandler = (err, req, res, next) => {
    let customError = err;

    if (customError.message == "Syntax Error")
        customError = new CustomError("Unexpected syntax", 400);

    res.status(customError.status || 500).json({
        success: false,
        message: customError.message
    });
};

module.exports = customErrorHandler;
