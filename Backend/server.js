const express = require("express");
const dotenv = require("dotenv");
const { connectDatabase } = require("./helpers/db");
const routers = require("./routers/index");

const app = express()

dotenv.config({
    path: "./config/config.env"
});

connectDatabase();

app.use(express.json());
app.use("/api", routers);

app.listen(process.env.PORT, () => {
    console.log(`BookStoreBackend listening at port ${process.env.PORT} in ${process.env.NODE_ENV} mode`);
});
