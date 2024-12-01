import express, { Application, Request, Response } from "express";
import bodyParser from "body-parser";
import producstRoute from "./api/routes/productRoute";

const app: Application = express();
const PORT: number = 8080;

app.use(bodyParser.json());

app.get("/health", (req: Request, res: Response) => {
    res.json({ message: "Welcome to the BFF" });
});

app.use("/api/v1", producstRoute.router);

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
  
export default app;
  