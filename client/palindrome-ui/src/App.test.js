import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import App from './App';
import palindromeService from './services/palindromeService';

// mock the palindromeService
jest.mock('./services/palindromeService', () => ({
  findClosestPalindrome: jest.fn(),
}));

describe('App Component', () => {
  it('renders the component correctly', () => {
    const { getByText, getByLabelText } = render(<App />);
    
    expect(getByText('Palindrome')).toBeInTheDocument();
    expect(getByLabelText('Number')).toBeInTheDocument();
    expect(getByText('Find closest palindrome')).toBeInTheDocument();
  });

  it('handles finding the closest palindrome', async () => {
    const { getByLabelText, getByText } = render(<App />);
    const numberInput = getByLabelText('Number');
    const button = getByText('Find closest palindrome');

    // mock the response from the service
    palindromeService.findClosestPalindrome.mockResolvedValue('12321');

    // simulate user input
    fireEvent.change(numberInput, { target: { value: '12345' } });
    
    // simulate button click
    fireEvent.click(button);

    // wait for the result
    await waitFor(() => {
      expect(getByText('The answer is:')).toBeInTheDocument();
      expect(getByText('12321')).toBeInTheDocument();
    });
  });
});
