const baseURL = process.env.REACT_APP_API_URL;

const apiService = {
  composeRequestURL(endpoint) {
    // other url processing if needed
    const url = `${baseURL}/${endpoint}`;
    return url;
  }
};

export default apiService;