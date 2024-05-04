import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class NZPArgTest {

    @Test
    public void testValidArgument() {
        NZPArg nzpArg = new NZPArg();
        try {
            nzpArg.validArgument("N");
            nzpArg.validArgument("Z");
            nzpArg.validArgument("P");
            nzpArg.validArgument("NZ");
            nzpArg.validArgument("NP");
            nzpArg.validArgument("ZP");
            nzpArg.validArgument("NZP");
        } catch (InvalidArgumentException e) {
            fail("Unexpected InvalidArgumentException");
        }
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgument() throws InvalidArgumentException {
        NZPArg nzpArg = new NZPArg();
        nzpArg.validArgument("A");
    }

    @Test
    public void testConvertToBinString() {
        NZPArg nzpArg = new NZPArg();
        assertEquals("100", nzpArg.convertToBinString("N"));
        assertEquals("010", nzpArg.convertToBinString("Z"));
        assertEquals("001", nzpArg.convertToBinString("P"));
        assertEquals("110", nzpArg.convertToBinString("NZ"));
        assertEquals("101", nzpArg.convertToBinString("NP"));
        assertEquals("011", nzpArg.convertToBinString("ZP"));
        assertEquals("111", nzpArg.convertToBinString("NZP"));
    }

    @Test
    public void testConvertToBinStringInvalidArgument() {
        NZPArg nzpArg = new NZPArg();
        assertEquals("Big man ting you didn't properly validate the argument", nzpArg.convertToBinString("A"));
    }
}
