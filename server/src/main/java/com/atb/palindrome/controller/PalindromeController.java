package com.atb.palindrome.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.atb.palindrome.service.PalindromeService;

@RestController
@Tag(name = "Palindrome API", description = "endpoints from PalindromeController")
public class PalindromeController {

    private PalindromeService palindromeService;

    @Autowired
    public PalindromeController(PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    /**
     * @param number String
     * @return ResponseEntity containing the closest palindrome as a string
     */
    @Operation(
    		summary = "Find the Closest Palindrome",
    		description = "Given a string `n` representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.")
    @GetMapping(value = "/palindrome/{number}")
    public ResponseEntity<Object> palindrome(@Parameter(description = "String representing an integer") @PathVariable String number){
        long num = Long.parseLong(number);
        long closestPalindrome = palindromeService.findClosestPalindrome(num);
        return ResponseEntity.ok().body("" + closestPalindrome);
    }
}
