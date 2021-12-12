package ua.lviv.iot.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StackTests {
    @Test
    void  pushToEmptyStackTest() {
        Stack stack = new Stack();
        stack.push(4);
        assertEquals(4, stack.peek());
    }

    @Test
    void pushToOrdinaryStackTest() {
        Stack stack = new Stack();
        stack.push(4);
        assertEquals(4, stack.peek());
        stack.push(2);
        assertEquals(2, stack.peek());
    }

    @Test
    void peekFromEmptyStackTest(){
        Stack stack = new Stack();
        assertThrows(RuntimeException.class, stack::peek);
    }

    @Test
    void peekFromOrdinaryStackTest(){
        Stack stack = new Stack();
        stack.push(0);
        stack.push(4);
        assertEquals(4, stack.peek());
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(2, stack.peek());
    }

    @Test
    void popFromEmptyStackTest(){
        Stack stack = new Stack();
        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    void popFromOneElemStackTest(){
        Stack stack = new Stack();
        stack.push(1);
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void popFromOrdinaryStackTest(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        assertEquals(7, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
        assertThrows(RuntimeException.class, stack::pop);
    }

    @Test
    void searchInEmptyStackTest(){
        Stack stack = new Stack();
        assertEquals(-1, stack.search(9));
    }

    @Test
    void searchInOrdinaryStackTest(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        assertEquals(3, stack.search(1));
        assertEquals(2, stack.search(3));
        assertEquals(1, stack.search(5));
        assertEquals(0, stack.search(7));
        assertEquals(-1, stack.search(9));
    }

    @Test
    void getFromEmptyStackTest(){
        Stack stack = new Stack();
        assertThrows(RuntimeException.class, () -> stack.get(0));
        assertThrows(RuntimeException.class, () -> stack.get(4));
    }

    @Test
    void getFromOrdinaryStackTest(){
        Stack stack = new Stack();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        assertEquals(7, stack.get(0));
        assertEquals(5, stack.get(1));
        assertEquals(3, stack.get(2));
        assertEquals(1, stack.get(3));
        assertThrows(RuntimeException.class,() -> stack.get(4));
    }

}
