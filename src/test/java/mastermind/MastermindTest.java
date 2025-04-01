package mastermind;
import org.junit.jupiter.api.Test;

import static mastermind.Mastermind.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindTest {

    @Test
    public void testGenerateSecretCodeFillsArrayWithinRange() {
        int[] code = new int[CODE_LENGTH];
        int[] secretCode = generateSecretCode(code, MAX_DIGIT);

        for (int digit : secretCode) {
            assertTrue(digit >= 0, "Digit should be at least 0");
            assertTrue(digit < MAX_DIGIT,
                    "Digit should be less than Mastermind.MAX_DIGIT (" + MAX_DIGIT + ")");
        }
    }
}
