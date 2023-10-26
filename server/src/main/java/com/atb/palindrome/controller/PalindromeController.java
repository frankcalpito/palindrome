package com.atb.palindrome.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.atb.palindrome.service.PalindromeService;

@RestController
@Tag(name = "Palindrome API", description = "endpoints from PalindromeController")
@Validated
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
    public ResponseEntity<Object> palindrome(
            @Parameter(description = "String representing an integer")
            @PathVariable @Pattern(regexp = "^\\d+$", message = "Invalid number format") // check for a valid integer format
            @Min(value = 0, message = "Number is too small") // check for min value
            @Max(value = Long.MAX_VALUE, message = "Number is too large") // check for max value
            String number
    ){
        long num = Long.parseLong(number);

        // Do not accept negative numbers
        if (num < 0) {
            return ResponseEntity.badRequest().body(  "Number must be non-negative");
        }
    
        // long closestPalindrome = palindromeService.findClosestPalindrome(num);
        String closestPalindrome = palindromeService.findClosestPalindrome2(number);

        return ResponseEntity.ok().body("" + closestPalindrome);
    }

    /**
     * @param number String
     * @return ResponseEntity containing the closest palindrome as a string
     */
    @Operation(
        summary = "Find the Closest Palindrome (Old)",
        description = "Given a string `n` representing an integer, return the closest integer (not including itself), which is a palindrome. If there is a tie, return the smaller one.")
    @GetMapping(value = "/palindrome-old/{number}")
    public ResponseEntity<Object> palindromeEfficient(
            @Parameter(description = "String representing an integer")
            @PathVariable @Pattern(regexp = "^\\d+$", message = "Invalid number format") // check for a valid integer format
            @Min(value = 0, message = "Number is too small") // check for min value
            @Max(value = Long.MAX_VALUE, message = "Number is too large") // check for max value
            String number
    ){
        long num = Long.parseLong(number);

        // Do not accept negative numbers
        if (num < 0) {
            return ResponseEntity.badRequest().body(  "Number must be non-negative");
        }
    
         long closestPalindrome = palindromeService.findClosestPalindrome(num);

        return ResponseEntity.ok().body("" + closestPalindrome);
    }
}
