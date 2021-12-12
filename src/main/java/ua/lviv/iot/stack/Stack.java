package ua.lviv.iot.stack;


public class Stack {
    private Node head;

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
            throw new RuntimeException("Stack is empty, nothing to peek");
        }
        return head.value;
    }

    public Integer pop() throws RuntimeException {
        Integer temp = peek();
        if (head.next == null) {
            head = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        return temp;
    }

    public Integer get(int position) throws RuntimeException{
        Node temp = this.head;
        for (int i = 0; i<position; i++){
            if(temp.next != null) {
                temp = temp.next;
            }
            else{
                throw new RuntimeException("Index out of range");
            }
        }
        return temp.value;
    }

    public Integer search(Integer valueToFind){
        Node currentNode = head;
        Integer i = 0;
        while(currentNode != null) {
            if (valueToFind.equals(currentNode.value)){
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
}
