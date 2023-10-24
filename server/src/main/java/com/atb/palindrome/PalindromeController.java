package com.atb.palindrome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PalindromeController {

    @Autowired
    private PalindromeService palindromeService;

    /**
     * Given a string `n` representing an integer, return the closest integer
     * (not including itself), which is a palindrome. If there is a tie,
     * return the smaller one.
     * 
     * @param number String 
     * @return
     */
    @GetMapping(value = "/palindrome/{number}")
    public ResponseEntity<Object> palindrome(@PathVariable String number){
        long num = Long.parseLong(number);
        long closestPalindrome = palindromeService.findClosestPalindrome(num);
        return ResponseEntity.ok().body("" + closestPalindrome);
    }


}
