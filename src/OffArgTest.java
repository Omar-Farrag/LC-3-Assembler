import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class OffArgTest {

    @Test
    public void testValidArgument() {
        OffArg offArg = new OffArg(8);
        try {
            offArg.validArgument("0x7F");
            offArg.validArgument("0xFF");
            offArg.validArgument("0x80");
            offArg.validArgument("0x00");
        } catch (InvalidArgumentException e) {
            fail("Unexpected InvalidArgumentException");
        }
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentFormat() throws InvalidArgumentException {
        OffArg offArg = new OffArg(8);
        offArg.validArgument("7F");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentValue() throws InvalidArgumentException {
        OffArg offArg = new OffArg(8);
        offArg.validArgument("0x100");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentValueNegative() throws InvalidArgumentException {
        OffArg offArg = new OffArg(8);
        offArg.validArgument("0xFFFF");
    }

    @Test
    public void testConvertToBinString() {
        OffArg offArg = new OffArg(8);
        assertEquals("01111111", offArg.convertToBinString("0x7F"));
        assertEquals("11111111", offArg.convertToBinString("0xFF"));
        assertEquals("10000000", offArg.convertToBinString("0x80"));
        assertEquals("00000000", offArg.convertToBinString("0x00"));
    }

}
