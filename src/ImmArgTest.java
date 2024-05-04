import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ImmArgTest {

    @Test
    public void testValidArgument() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        assertTrue(immArg.validArgument("#15"));
        assertTrue(immArg.validArgument("#-16"));
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentFormat() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("15");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentValue() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("#32");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidNegativeArgumentValue() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("#-17");
    }

    @Test
    public void testConvertToBinString() {
        ImmArg immArg = new ImmArg(5);
        String result = immArg.convertToBinString("#15");
        assertEquals("01111", result);
    }

    @Test
    public void testConvertToBinStringWithZeroPadding() {
        ImmArg immArg = new ImmArg(5);
        String result = immArg.convertToBinString("#1");
        assertEquals("00001", result);
    }

    @Test
    public void testConvertToBinStringNegativeNumber() {
        ImmArg immArg = new ImmArg(5);
        String result = immArg.convertToBinString("#-1");
        assertEquals("11111", result);
    }

    @Test
    public void testConvertToBinStringNegativeNumberWithZeroPadding() {
        ImmArg immArg = new ImmArg(5);
        String result = immArg.convertToBinString("#-16");
        assertEquals("10000", result);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentNull() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument(null);
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentEmpty() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentNotStartsWithHash() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("15");
    }

    @Test(expected = InvalidArgumentException.class)
    public void testInvalidArgumentNotNumber() throws InvalidArgumentException {
        ImmArg immArg = new ImmArg(5);
        immArg.validArgument("#abc");
    }

}
