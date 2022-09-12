import React from "react";
import { useParams } from 'react-router-dom';
import Typography from "@mui/material/Typography";


export default function CodeDisplay() {

    const { codeId } = useParams();

    return <Typography>Code id {codeId}</Typography>

}