import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FixedArgTest {

    @Test
    public void testValidArgument() {
        FixedArg fixedArg = new FixedArg("1101");
        try {
            boolean result = fixedArg.validArgument("1101");
            assertEquals(true, result);
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConvertToBinString() {
        FixedArg fixedArg = new FixedArg("1101");
        String result = fixedArg.convertToBinString("");
        assertEquals("1101", result);
    }

}
