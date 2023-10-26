package com.atb.palindrome;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.atb.palindrome.service.PalindromeService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class PalindromeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PalindromeService palindromeService;

    @Test
    public void testPalindromeLower() throws Exception {
        String inputNumber = "106"; // tie for 111 and 101
        String expectedPalindrome = "101"; // should return 101 as it's less than 111

        when(palindromeService.findClosestPalindrome2(inputNumber))
            .thenReturn(expectedPalindrome);

        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPalindrome)));
    }

    @Test
    public void testPalindromeHigher() throws Exception {
        String inputNumber = "119";
        String expectedPalindrome = "121";

        when(palindromeService.findClosestPalindrome2(inputNumber))
            .thenReturn(expectedPalindrome);
        
        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPalindrome)));
    }

    @Test
    public void testOldPalindromeLower() throws Exception {
        String inputNumber = "106"; // tie for 111 and 101
        long expectedPalindrome = 101; // should return 101 as it's less than 111

        when(palindromeService.findClosestPalindrome(Long.parseLong(inputNumber)))
            .thenReturn(expectedPalindrome);

        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome-old/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPalindrome)));
    }

    @Test
    public void testOldPalindromeHigher() throws Exception {
        String inputNumber = "119";
        long expectedPalindrome = 121;

        when(palindromeService.findClosestPalindrome(Long.parseLong(inputNumber)))
            .thenReturn(expectedPalindrome);

        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome-old/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedPalindrome)));
    }

    @Test
    public void testPalindromeNegativeNumber() throws Exception {
        String inputNumber = "-5"; // A negative number

        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Number must be non-negative"));
    }

    @Test
    public void testOldPalindromeNegativeNumber() throws Exception {
        String inputNumber = "-5"; // A negative number

        mockMvc.perform(MockMvcRequestBuilders.get("/palindrome-old/{number}", inputNumber)
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Number must be non-negative"));
    }
}
