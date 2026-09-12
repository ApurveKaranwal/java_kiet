/**
 * Case Study 5: Library Management System
 * Book with ID, title, author, availability status.
 * issueBook() and returnBook() — cannot issue if already issued.
 */
public class CaseStudy5 {

    static class Book {
        private int bookId;
        private String title;
        private String author;
        private boolean available;

        public Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public void issueBook() {
            if (available) {
                available = false;
                System.out.println("Book issued successfully: \"" + title + "\"");
            } else {
                System.out.println("Cannot issue! Book \"" + title + "\" is already issued.");
            }
        }

        public void returnBook() {
            if (!available) {
                available = true;
                System.out.println("Book returned successfully: \"" + title + "\"");
            } else {
                System.out.println("Book \"" + title + "\" was not issued. It is available.");
            }
        }

        public void displayBook() {
            System.out.println("Book ID     : " + bookId);
            System.out.println("Title       : " + title);
            System.out.println("Author      : " + author);
            System.out.println("Status      : " + (available ? "Available" : "Issued"));
        }
    }

    public static void main(String[] args) {
        Book b1 = new Book(101, "Data Structures", "Cormen");
        Book b2 = new Book(102, "Operating Systems", "Galvin");

        System.out.println("--- Displaying Books ---");
        b1.displayBook();
        System.out.println();
        b2.displayBook();
        System.out.println();

        System.out.println("--- Issuing Books ---");
        b1.issueBook();
        b2.issueBook();
        b1.issueBook(); // try issuing again
        System.out.println();

        System.out.println("--- Returning Books ---");
        b1.returnBook();
        b1.returnBook(); // try returning again
    }
}
