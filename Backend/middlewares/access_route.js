const CustomError = require("../helpers/CustomError");
const { isTokenIncluded, getAccessTokenFromHeader } = require("../helpers/token");
const jwt = require("jsonwebtoken");

const getAccessToRoute = (req, res, next) => {
    const { JWT_SECRET_KEY } = process.env;

    if (!isTokenIncluded(req))
        return next(new CustomError("Missing token", 401));

    const accessToken = getAccessTokenFromHeader(req);

    jwt.verify(accessToken, JWT_SECRET_KEY, (err, decoded) => {
        if (err)
            return next(new CustomError("Not authorized to access", 401));

        req.user = {
            id: decoded.id,
            mail: decoded.mail,
            role: decoded.role
        };

        next();
    });
};

module.exports = {
    getAccessToRoute
}
