import { Typography } from "@mui/material";
import { Link } from "react-router-dom";


export default function Login() {

    return (
        <>
            <Typography>Login View</Typography>
            <Link to="app">Entrar</Link>
            <Link to="register">Registro</Link>
        </>
    );

}