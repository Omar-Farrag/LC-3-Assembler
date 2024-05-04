import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class RegArgTest {

    @Test
    public void testValidArgument() {
        RegArg regArg = new RegArg(3);
        try {
            regArg.validArgument("R0");
            regArg.validArgument("R7");
        } catch (InvalidArgumentException e) {
            fail("Unexpected InvalidArgumentException");
        }
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentFormat() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("0");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentFormatNoR() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("0");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentFormatNoRWithValidNumber() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("1");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentValue() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("R8");
    }

    @Test
    public void testConvertToBinString() {
        RegArg regArg = new RegArg(3);
        assertEquals("000", regArg.convertToBinString("R0"));
        assertEquals("111", regArg.convertToBinString("R7"));
    }

    @Test
    public void testValidArgumentMaxValue() {
        RegArg regArg = new RegArg(3);
        try {
            regArg.validArgument("R7");
        } catch (InvalidArgumentException e) {
            fail("Unexpected InvalidArgumentException");
        }
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentMaxValueExceeded() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("R8");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentNegativeValue() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("R-1");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentNonNumeric() throws InvalidArgumentException {
        RegArg regArg = new RegArg(3);
        regArg.validArgument("RA");
    }

    @Test
    public void testConvertToBinStringMaxValue() {
        RegArg regArg = new RegArg(3);
        assertEquals("111", regArg.convertToBinString("R7"));
    }

    @Test
    public void testConvertToBinStringWithLeadingZeros() {
        RegArg regArg = new RegArg(3);
        assertEquals("010", regArg.convertToBinString("R2"));
    }

}
