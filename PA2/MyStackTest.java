import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import utilities.FullStackException;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    String[] evens;
    MyStack s1;
    MyStack s2;
    @BeforeEach
    public void initEvens() {
        evens = new String[5];
        for (int i = 0; i < 5; i++) {
            evens[i] = "" + (i * 2);
        }
        // ["0", "2", "4", "6", "8"]
    }

    @BeforeEach
    public void initDefaultConstructor() {
        s1 = new MyStack();
    }
    @Test
    public void testDefaultConstructor() {
        assertEquals(10, s1.capacity());
        assertEquals(0, s1.size());
    }

    @BeforeEach
    public void initCustomConstructor() {
        s2 = new MyStack(3);
    }

    @Test
    public void testCustomConstructor() {
        assertEquals(3, s2.capacity());
        assertEquals(0, s2.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(s1.isEmpty());
        assertTrue(s2.isEmpty());
    }

    @Test
    public void testPushDefault() {
        s1.push("Start");
        assertEquals(1, s1.size());
        for (int i = 0; i < 5; i++) {
            s1.push(evens[i]);
        }
        assertEquals("8", s1.peek());
    }

    @Test
    public void testPushException() {
        for(int i = 0; i < 5; i++) {
            s1.push(evens[i]);
            s1.push(evens[i]);
        }

        Assertions.assertThrows(FullStackException.class, () -> {
            s1.push("too much");
        });

    }
}
