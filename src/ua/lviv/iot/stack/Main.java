package ua.lviv.iot.stack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner in = new Scanner(System.in);
        System.out.println("\n\nThis program simulates stack data structure.\nTo see available commands type: /help");
        while (true) {
            stack.decider(in.nextLine());
        }
    }
}
