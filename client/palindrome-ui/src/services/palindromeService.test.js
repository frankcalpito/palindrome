import axios from 'axios';
import palindromeService from './palindromeService';

// Mock axios to simulate HTTP requests
jest.mock('axios');

describe('palindromeService', () => {
  it('should return response data when the request is successful', async () => {
    const responseData = { result: 'palindrome' };
    axios.get.mockResolvedValue({ status: 200, data: responseData });

    const number = 12345;
    const result = await palindromeService.findClosestPalindrome(number);

    expect(result).toEqual(responseData);
  });

  it('should handle unexpected status codes', async () => {
    axios.get.mockResolvedValue({ status: 404 });

    const number = 54321;
    try {
      await palindromeService.findClosestPalindrome(number);
    } catch (error) {
      expect(error.message).toBe('Unexpected status code');
    }
  });

  it('should handle errors gracefully', async () => {
    axios.get.mockRejectedValue(new Error('Network error'));

    const number = 9876;
    const result = await palindromeService.findClosestPalindrome(number);

    expect(result).toBe('Error: Network error');
  });
});
