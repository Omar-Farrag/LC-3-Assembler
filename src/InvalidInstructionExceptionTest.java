import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InvalidInstructionExceptionTest {

    @Test
    public void testConstructor() {
        InvalidInstructionException exception = new InvalidInstructionException("Test message");
        assertEquals("Test message", exception.getMessage());
    }

}
