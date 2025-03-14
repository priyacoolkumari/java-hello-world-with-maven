package hello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HelloWorldTest {

    @Test
    void testHelloWorldMessage() {
        String message = "Hello, World!";
        assertEquals("Hello, World!", message, "Message should be 'Hello, World!'");
    }

    @Test
    void testAddition() {
        int sum = 2 + 3;
        assertEquals(5, sum, "2 + 3 should be 5");
    }

    @Test
    void testStringNotNull() {
        String str = "JUnit Test";
        assertNotNull(str, "String should not be null");
    }

    @Test
    void testBooleanCondition() {
        boolean isTrue = (5 > 3);
        assertTrue(isTrue, "5 is greater than 3, so condition should be true");
    }
}
