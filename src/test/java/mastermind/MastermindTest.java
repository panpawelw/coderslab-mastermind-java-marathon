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
    void testVerifyInputValidInput() {
        String input = "1234";
        int codeLength = 4;
        int maxDigit = 6;

        int[] expected = {1, 2, 3, 4};
        int[] result = verifyInput(input, codeLength, maxDigit);

        assertArrayEquals(expected, result, "The converted array should match expected values.");
    }

    @Test
    void testVerifyInputInvalidLengthTooShort() {
        String input = "123";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(IllegalArgumentException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw IllegalArgumentException for short input length");
    }

    @Test
    void testVerifyInputInvalidLengthTooLong() {
        String input = "12345";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(IllegalArgumentException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw IllegalArgumentException for long input length");
    }

    @Test
    void testVerifyInputInvalidDigitTooLow() {
        String input = "0234";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(NumberFormatException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw NumberFormatException for digit less than 1");
    }

    @Test
    void testVerifyInputInvalidDigitTooHigh() {
        String input = "1784";
        int codeLength = 4;
        int maxDigit = 6;

        assertThrows(NumberFormatException.class, () ->
                        verifyInput(input, codeLength, maxDigit),
                "Should throw NumberFormatException for digit greater than maxDigit");
    }

    @Test
    void testVerifyInputEdgeDigits() {
        String input = "16";
        int codeLength = 2;
        int maxDigit = 6;

        int[] expected = {1, 6};
        int[] result = verifyInput(input, codeLength, maxDigit);

        assertArrayEquals(expected, result, "Should correctly parse edge digits 1 and maxDigit");
    }

    @Test
    void testFormatErrorMessageSingleDigit() {
        assertEquals("musi być 1 cyfra", Mastermind.formatErrorMessage(1));
    }

    @Test
    void testFormatErrorMessageMultipleDigits() {
        assertEquals("muszą być 2 cyfry", Mastermind.formatErrorMessage(2));

        assertEquals("muszą być 3 cyfry", Mastermind.formatErrorMessage(3));

        assertEquals("muszą być 4 cyfry", Mastermind.formatErrorMessage(4));
    }

    @Test
    void testFormatErrorMessageOtherCases() {
        assertEquals("musi być 5 cyfr", Mastermind.formatErrorMessage(5));

        assertEquals("musi być 10 cyfr", Mastermind.formatErrorMessage(10));
    }

    @Test
    void testCompareCodesAllInPlace() {
        int[] secret = {1, 2, 3, 4};
        int[] attempt = {1, 2, 3, 4};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(4, result.inPlace());
        assertEquals(0, result.outOfPlace());
        
    }

    @Test
    void testCompareCodesAllOutOfPlace() {
        int[] secret = {1, 2, 3, 4};
        int[] attempt = {4, 3, 2, 1};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(0, result.inPlace());
        assertEquals(4, result.outOfPlace());
    }

    @Test
    void testCompareCodesSomeInPlaceSomeOutOfPlace() {
        int[] secret = {1, 2, 3, 4};
        int[] attempt = {1, 3, 2, 4};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(2, result.inPlace());   // positions 0 and 3
        assertEquals(2, result.outOfPlace()); // 2 and 3 are swapped
    }

    @Test
    void testCompareCodesNoMatches() {
        int[] secret = {1, 2, 3, 4};
        int[] attempt = {5, 6, 7, 8};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(0, result.inPlace());
        assertEquals(0, result.outOfPlace());
    }

    @Test
    void testCompareCodesDuplicateDigits() {
        int[] secret = {1, 2, 2, 3};
        int[] attempt = {2, 2, 1, 3};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(2, result.inPlace());   // 2 at position 1, 3 at position 3
        assertEquals(2, result.outOfPlace()); // 1 and the other 2 are swapped
    }

    @Test
    void testCompareCodesSingleElementMatch() {
        int[] secret = {7};
        int[] attempt = {7};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(1, result.inPlace());
        assertEquals(0, result.outOfPlace());
    }

    @Test
    void testCompareCodesSingleElementMismatch() {
        int[] secret = {7};
        int[] attempt = {3};
        CodesComparison result = compareCodes(secret, attempt);
        assertEquals(0, result.inPlace());
        assertEquals(0, result.outOfPlace());
    }
}
