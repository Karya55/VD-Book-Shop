const isTokenIncluded = (req) => {
    return(req.headers.authorization && req.headers.authorization.startsWith('Bearer:'));
}

const getAccessTokenFromHeader = (req) => {
    const auth = req.headers.authorization;
    const access_token = auth.split(" ")[1];
    return access_token;
}

const sendJWT = (user, res) => {
    const token = user.generateJWT();

    res.status(200).json({
        success: true,
        data: {
            access_token: token,
            expiration: parseInt(process.env.JWT_EXPIRE.slice(0, -1)),
            userId: user._id
        }
    });
}

module.exports = {
    isTokenIncluded,
    getAccessTokenFromHeader,
    sendJWT
}
