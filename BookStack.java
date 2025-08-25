// Stack ADT for recently viewed books
public class BookStack {
    private String[] books; // array to store books
    private int top;        // index of the top book

    public BookStack(int capacity) {
        books = new String[capacity];
        top = -1; // empty stack
    }

    public void push(String book) { // add a book on top
        if (top == books.length - 1) throw new RuntimeException("Stack is full");
        books[++top] = book;
    }

    public String pop() { // remove the top book
        if (top == -1) throw new RuntimeException("Stack is empty");
        return books[top--];
    }

    public String peek() { // look at the top book without removing
        if (top == -1) throw new RuntimeException("Stack is empty");
        return books[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // Main method to run the program
    public static void main(String[] args) {
        BookStack stack = new BookStack(5);

        stack.push("The Hobbit");
        stack.push("1984");
        stack.push("Dune");

        System.out.println("Top book: " + stack.peek()); // Dune
        System.out.println("Popped: " + stack.pop());    // Dune
        System.out.println("Top book now: " + stack.peek()); // 1984
    }
}