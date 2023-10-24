import axios from 'axios';
import apiService from './apiService'

const palindromeService = {
  async findClosestPalindrome(number) {
    const endpointURL = apiService.composeRequestURL(`palindrome/${number}`);

    try {
      // Make the GET request
      const response = await axios.get(endpointURL);

      // Handle a successful response here
      if (response.status === 200) {
        // You can access the response data using response.data
        console.log('Success:', response.data);
        return response.data;
      } else {
        // Handle other HTTP status codes if needed
        console.error('Unexpected status code:', response.status);
        throw new Error('Unexpected status code');
      }
    } catch (error) {
      // Handle errors here
      console.error('Error:', error);
      throw error;
    }
  },
};

export default palindromeService;
