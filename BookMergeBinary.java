import java.util.*;

class Book {
    String title;
    String author;
    double price;

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + " by " + author + " - $" + price;
    }
}

public class BookMergeBinary {

    // Generic merge sort that uses a Comparator
    public static void mergeSort(Book[] books, int left, int right, Comparator<Book> comparator) {
        if (left < right) {
            int mid = (left + right) / 2;
            
            mergeSort(books, left, mid, comparator);
            mergeSort(books, mid + 1, right, comparator);
            merge(books, left, mid, right, comparator);
        }
    }

    public static void merge(Book[] books, int left, int mid, int right, Comparator<Book> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Book[] L = new Book[n1];
        Book[] R = new Book[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = books[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = books[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(L[i], R[j]) <= 0) {
                books[k] = L[i];
                i++;
            } else {
                books[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            books[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            books[k] = R[j];
            j++;
            k++;
        }
    }

    // Binary search for a book by title
    public static Book binarySearchByTitle(Book[] books, String targetTitle) {
        int low = 0;
        int high = books.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = books[mid].title.compareToIgnoreCase(targetTitle);

            if (comparison == 0) {
                return books[mid]; // Found the book
            } else if (comparison < 0) {
                low = mid + 1; // Search right half
            } else {
                high = mid - 1; // Search left half
            }
        }
        return null; // Not found
    }

    public static Book binarySearchByAuthor(Book[] books, String targetAuthor) {
    int low = 0;
    int high = books.length - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        int comparison = books[mid].author.compareToIgnoreCase(targetAuthor);

        if (comparison == 0) {
            return books[mid]; // Found the book
        } else if (comparison < 0) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return null;
}

    public static void main(String[] args) {
        Book[] books = {
            new Book("The Hobbit", "J. R. R. Tolkien", 29.99),
            new Book("1984", "George Orwell", 19.99),
            new Book("Dune", "Frank Herbert", 39.50),
        };

        // Sort by Title
        System.out.println("Sorted by Title:");
        mergeSort(books, 0, books.length - 1, Comparator.comparing(b -> b.title));
        for (Book b : books) System.out.println(b);
        
        // Sort by Author
        System.out.println("\nSorted by Author:");
        mergeSort(books, 0, books.length - 1, Comparator.comparing(b -> b.author));
        for (Book b : books) System.out.println(b);

        // // Sort by Price
        System.out.println("\nSorted by Price:");
        mergeSort(books, 0, books.length - 1, Comparator.comparingDouble(b -> b.price));
        for (Book b : books) System.out.println(b);

        // Book result = binarySearchByTitle(books, "1984");
        Book result = binarySearchByAuthor(books, "Frank Herbert");

        if (result != null) {
            System.out.println("Book found: " + result.title + " by " + result.author);
        } else {
            System.out.println("Book not found.");
        }
    }
}
