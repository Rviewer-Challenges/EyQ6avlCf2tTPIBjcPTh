import axios from "axios";

const entpoint = "http://localhost:8080/api/languages";

export const fetchLanguage = async () => {
  return await axios.get<any>(entpoint);
};
