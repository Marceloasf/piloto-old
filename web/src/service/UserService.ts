import axios from "axios";

const resource = "/api/users";

export const findAll = () => {
  return axios.get(resource);
};
