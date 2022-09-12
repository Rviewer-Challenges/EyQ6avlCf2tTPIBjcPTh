import axios from "axios";
import { Code } from "../models/code";

const entpoint = "http://localhost:8080/api/snippetCodes";

export const fetchSnippetCode = async () => {
  return await axios.get<any>(entpoint);
};

export const fetchSnippetCodeFilter = async (
  title: string,
  langName: string,
  tags: string[]
) => {
  return await axios.get<any>(
    `${entpoint}/search/findCodeWithFilter?title=${title}&langName=${langName}&tags=${tags.join(
      ","
    )}`
  );
};
