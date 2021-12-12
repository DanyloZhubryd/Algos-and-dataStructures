package ua.lviv.iot.stack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(4);
        stack.push(1);
        stack.push(6);
        stack.printStack();
        System.out.println("peek: " + stack.peek());
        System.out.println("1st pop: " + stack.pop());
        System.out.println("2nd pop: " + stack.pop());
        System.out.println("3rd pop: " + stack.pop());
    }
}
