package mastermind;
import org.junit.jupiter.api.Test;

import static mastermind.Mastermind.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
