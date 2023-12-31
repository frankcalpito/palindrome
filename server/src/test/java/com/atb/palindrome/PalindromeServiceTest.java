package com.atb.palindrome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atb.palindrome.service.PalindromeService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PalindromeServiceTest {

    private PalindromeService palindromeService;
   
    @Autowired
    public PalindromeServiceTest(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @Test
    public void testFindClosestPalindrome() {
        long inputNumber = 123;
        long expectedPalindrome = 121;

        long closestPalindrome = palindromeService.findClosestPalindrome(inputNumber);

        assertEquals(expectedPalindrome, closestPalindrome);
    }

    @Test
    public void testFindClosestPalindrome2() {
        String inputNumber = "123";
        String expectedPalindrome = "121";

        String closestPalindrome = palindromeService.findClosestPalindrome2(inputNumber);

        assertEquals(expectedPalindrome, closestPalindrome);
    }

    @Test
    public void testFindClosestPalindromeLargeNumber() {
        String inputNumber = "9223372036854716000";
        String expectedPalindrome = "9223372036302733229";

        String closestPalindrome = palindromeService.findClosestPalindrome2(inputNumber);

        assertEquals(expectedPalindrome, closestPalindrome);
    }

    @Test
    public void testIsPalindrome() {
        // Test a few cases of isPalindrome
        long palindromeNumber = 12321;
        long nonPalindromeNumber = 12345;

        boolean isPalindrome = palindromeService.isPalindrome(palindromeNumber);
        boolean isNotPalindrome = palindromeService.isPalindrome(nonPalindromeNumber);

        assertEquals(true, isPalindrome);
        assertEquals(false, isNotPalindrome);
    }
}
