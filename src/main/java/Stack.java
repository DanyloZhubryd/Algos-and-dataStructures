package ua.lviv.iot.stack;


public class Stack {
    Node head;

    private class Node {
        Integer value;
        Node next;
        Node previous;

        public Node(Integer value) {
            this.value = value;
        }
    }
    public Stack() {
    }

    public Boolean isEmpty(){
        return head == null;
    }

    public void push(Integer value) {
        if (isEmpty()) {
            head = new Node(value);
        } else {
            Node temp = head;
            head = new Node(value);
            head.next = temp;
            temp.previous = head;
        }
    }

    public Integer peek() throws RuntimeException {
        if (isEmpty()){
            throw new RuntimeException("Stack is empty, nothing to pick");
        }
        return head.value;
    }

    public Integer pop() throws RuntimeException {
        Integer temp = peek();
        head = head.next;
        head.previous = null;
        return temp;
    }

    public Node get(int position) throws RuntimeException{
        Node temp = this.head;
        for (int i = 1; i< position; i++){
            if(temp.next != null) {
                temp = temp.next;
            }
            else{
                throw new RuntimeException("Index out of range");
            }
        }
        return temp;
    }

    public Integer search(Integer elemToFind){
        Node currentNode = head;
        Integer i = 0;
        while(currentNode != null) {
            if (elemToFind.equals(currentNode.value)){
                return i;
            }
            currentNode = currentNode.next;
            i++;
        }
        i = -1;
        return i;
    }

    public void printStack() {
        if (this.head == null) {
            System.out.println("Stack is empty");
        } else {
            System.out.println("Elements inside stack:");
            System.out.print("TOP —> ");
            Node temp = this.head;
            while (temp != null) {
                System.out.print(temp.value + " ");
                temp = temp.next;

            }
            System.out.println("<— BOTTOM");
        }
    }

    public void decider(String input) throws RuntimeException{
        String[] arr = input.split(" ");
        switch (arr[0]) {
            case ("/push") -> {
                for (int i = 1; i < arr.length; i++) {
                    this.push(java.lang.Integer.parseInt(arr[i]));
                }
            }
            case ("/pop") -> System.out.println("deleted element: " + this.pop());
            case ("/peek") -> System.out.println("value on top of stack: " + this.peek());
            case ("/exit") -> System.exit(0);
            case("/get") -> System.out.println(this.get(java.lang.Integer.parseInt(arr[1])).value);
            case ("/printStack") -> this.printStack();
            case ("/help") -> System.out.println("""
                    /push <num1> <num2> <num...> - add numbers to a stack
                    /peek - get number on top of stack
                    /pop - delete element on top of a stack
                    /exit - exit the program
                    /help - print this window
                    /get <pos> - get value of an element on <pos> position (1st element`s position is 1)
                    /printStack - print inners of a stack.""");
            default -> System.out.println(input + " is not recognized as internal command");
        }
    }

}
