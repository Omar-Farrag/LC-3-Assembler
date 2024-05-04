import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InvalidArgumentExceptionTest {

    @Test
    public void testConstructor() {
        InvalidArgumentException exception = new InvalidArgumentException("Test message");
        assertEquals("Test message", exception.getMessage());
    }

}
