import axios from "axios";

const entpoint = "http://localhost:8080/api/tags";

export const fetchTags = async () => {
  return await axios.get<any>(entpoint);
};
