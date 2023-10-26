package com.atb.palindrome.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PalindromeService {

    public long findClosestPalindrome(long num) {
    	for (int i = 1;; i++) {
    		if (isPalindrome(num - i))
    			return num - i;
    		if (isPalindrome(num + i))
    			return num + i;
        }
    }

    /**
     * algorithm from https://www.geeksforgeeks.org/closest-palindrome-number-whose-absolute-difference-min/
     * 
     * fixed bugs for some scenarios (9223372036854716000)
     * 
     * @param n
     * @return
     */
    public String findClosestPalindrome2(String n) {

        // Initialize a list to store the candidate palindromic numbers
        List<Long> candidates = new ArrayList<>();
 
        // Get the length of the input number n
        int length = n.length();
 
        // Calculate the index of the middle element (or the element just after the middle for even-length numbers)
        int mid = (length + 1) / 2;
 
        // If the input number has only one digit, the closest palindromic number is (n-1) except when it's 0
        if (length == 1) {
            long num = Long.parseLong(n);
            return String.valueOf(num == 0 ? 1 : num - 1);
        }
 
        // Extract the prefix (first half) of the input number
        long prefix = Long.parseLong(n.substring(0, mid));
 
        // Add candidate: (10^n + 1) if it's not negative
        long candidate1 = (long) Math.pow(10, length) + 1;
        if (candidate1 > 0) {
        	candidates.add(candidate1);
        }
        
        // Add candidate: (10^(n-1) - 1)
        candidates.add((long) Math.pow(10, length - 1) - 1);
 
        // Generate three possible prefixes by incrementing and decrementing the original prefix
        List<Long> temp = Arrays.asList(prefix, prefix + 1, prefix - 1);
 
        // Construct the candidate palindromic numbers using the generated prefixes
        for (Long i : temp) {
        	try {
                String res = String.valueOf(i);
                // If the length of the input number is odd, exclude the last character while constructing the palindromic number
                if ((length & 1) != 0) {
                    res = res.substring(0, res.length() - 1);
                }
                // Create the palindromic number by appending the reverse of the prefix
                String peep = i + new StringBuilder(res).reverse().toString();
                candidates.add(Long.parseLong(peep));
        	} catch (Exception e) {
        		// failed to parse value larger than max long
        		System.out.println(e);
        	}
        }
 
        // Initialize variables to keep track of the minimum difference and the closest palindromic number
        long minDiff = Long.MAX_VALUE;
        long result = Long.parseLong(n);
        long tip = Long.parseLong(n);
 
        // Iterate through the candidate palindromic numbers and find the closest one to the input number
        for (int i = 0; i < candidates.size(); i++) {
            long candidate = candidates.get(i);
            if (candidate != tip && minDiff > Math.abs(candidate - tip)) {
                result = candidate;
                minDiff = Math.abs(candidate - tip);
            } else if (Math.abs(candidate - tip) == minDiff) {
                result = Math.min(result, candidate);
            }
        }
 
        return String.valueOf(result);
    }

    public boolean isPalindrome(long num) {
        long original = num;
        long reversed = 0;
        
        while (original > 0) {
            reversed = 10 * reversed + original % 10;
            original /= 10;
        }
        
        return reversed == num;
    }
}
