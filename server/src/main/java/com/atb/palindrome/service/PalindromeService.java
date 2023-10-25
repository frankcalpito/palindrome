package com.atb.palindrome.service;

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
