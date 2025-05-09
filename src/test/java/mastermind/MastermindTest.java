package mastermind;
import org.junit.jupiter.api.Test;

import static mastermind.Mastermind.*;
import static org.junit.jupiter.api.Assertions.*;

public class MastermindTest {

    @Test
    public void testGenerateSecretCodeFillsArrayWithinRange() {
        int[] code = new int[CODE_LENGTH];
        int[] secretCode = generateSecretCode(code, MAX_DIGIT);

        for (int digit : secretCode) {
            assertTrue(digit >= 0, "Digit should be at least 0");
            assertTrue(digit <= MAX_DIGIT,
                    "Digit should be equal or less than Mastermind.MAX_DIGIT (" + MAX_DIGIT + ")");
        }
    }

    @Test
    void testVerifyInput_ValidInput() {
        String input = "1234";
        int codeLength = 4;
        int maxDigit = 6;

        int[] expected = {1, 2, 3, 4};
        int[] result = verifyInput(input, codeLength, maxDigit);

        assertArrayEquals(expected, result, "The converted array should match expected values.");
    }

    @Test
    void testVerifyInput_InvalidLength_TooShort() {
        String input = "123";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(IllegalArgumentException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw IllegalArgumentException for short input length");
    }

    @Test
    void testVerifyInput_InvalidLength_TooLong() {
        String input = "12345";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(IllegalArgumentException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw IllegalArgumentException for long input length");
    }

    @Test
    void testVerifyInput_InvalidDigit_TooLow() {
        String input = "0234";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(NumberFormatException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw NumberFormatException for digit less than 1");
    }

    @Test
    void testVerifyInput_InvalidDigit_TooHigh() {
        String input = "1784";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(NumberFormatException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw NumberFormatException for digit greater than maxDigit");
    }

    @Test
    void testVerifyInput_EdgeDigits() {
        String input = "16";
        int codeLength = 2;
        int maxDigit = 6;

        int[] expected = {1, 6};
        int[] result = verifyInput(input, codeLength, maxDigit);

        assertArrayEquals(expected, result, "Should correctly parse edge digits 1 and maxDigit");
    }

    @Test
    void testFormatErrorMessage_SingleDigit() {
        assertEquals("musi być 1 cyfra", Mastermind.formatErrorMessage(1));
    }

    @Test
    void testFormatErrorMessage_MultipleDigits() {
        assertEquals("muszą być 2 cyfry", Mastermind.formatErrorMessage(2));

        assertEquals("muszą być 3 cyfry", Mastermind.formatErrorMessage(3));

        assertEquals("muszą być 4 cyfry", Mastermind.formatErrorMessage(4));
    }

    @Test
    void testFormatErrorMessage_OtherCases() {
        assertEquals("musi być 5 cyfr", Mastermind.formatErrorMessage(5));

        assertEquals("musi być 10 cyfr", Mastermind.formatErrorMessage(10));
    }
}
