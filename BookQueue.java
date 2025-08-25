import java.util.*;

class Order {
    private String customerName;
    private String shippingAddress;
    private Map<String, Integer> books; // book title -> quantity

    public Order(String customerName, String shippingAddress) {
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.books = new HashMap<>();
    }

    public void addBook(String title, int quantity) {
        books.put(title, quantity);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Map<String, Integer> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Order from " + customerName + " (" + shippingAddress + ") " + books;
    }
}

// FIFO Queue for Orders
class OrderQueue {
    private LinkedList<Order> queue = new LinkedList<>();

    public void enqueue(Order order) {
        queue.addLast(order); // add to the rear
    }

    public Order dequeue() {
        if (queue.isEmpty()) throw new RuntimeException("No orders in queue");
        return queue.removeFirst(); // remove from the front
    }

    public Order peek() {
        if (queue.isEmpty()) throw new RuntimeException("No orders in queue");
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Demo
public class BookQueue {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        // Create sample orders
        Order order1 = new Order("Alice", "123 Main St");
        order1.addBook("The Hobbit", 1);
        order1.addBook("1984", 2);

        Order order2 = new Order("Bob", "456 Oak Ave");
        order2.addBook("Dune", 1);

        Order order3 = new Order("Phuc Dat Bich", "254 Nguyen Van Cu");
        order3.addBook("Anarchist Cookbook", 1);

        // Add orders to queue
        orderQueue.enqueue(order1);
        orderQueue.enqueue(order2);
        orderQueue.enqueue(order3);

        // Process orders (FIFO)
        System.out.println("Peek order: " + orderQueue.peek()); // Alice's order
        System.out.println("Dequeue: " + orderQueue.dequeue()); // remove Alice's order
        System.out.println("Dequeue: " + orderQueue.dequeue()); // remove Bob's order
        System.out.println("Peek after dequeue: " + orderQueue.peek()); // Phuc's order
    }
}
