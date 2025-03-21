package mastermind;
import org.junit.jupiter.api.Test;

import static mastermind.Mastermind.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindTest {

    @Test
    public void testGenerateSecretCodeFillsArrayWithinRange() {
        int[] code = new int[4]; // example size; adjust as needed
        int[] secretCode = generateSecretCode(code);
        // Assuming Mastermind.MAX_DIGIT is defined; if not, substitute a test value
        int maxDigit = Mastermind.MAX_DIGIT;

        for (int digit : secretCode) {
            assertTrue(digit >= 0, "Digit should be at least 0");
            assertTrue(digit < maxDigit, "Digit should be less than Mastermind.MAX_DIGIT (" + maxDigit + ")");
        }
    }
}
