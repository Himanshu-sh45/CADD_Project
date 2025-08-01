import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        library mybook = new mylibrary();

        System.out.print("HOW MANY STUDENTS VISIT LIBRARY THIS MONTH? ");
        int total = scanner.nextInt();
        scanner.nextLine(); // Clear newline

        // Arrays to store data
        String[] names = new String[total];
        int[] ids = new int[total];
        String[] issuedBooks = new String[total];
        String[] returnedBooks = new String[total];
        int purchasedBooks;

        // Loop for each student
        for (int i = 0; i < total; i++) {
            System.out.println("\n--- STUDENT " + (i + 1) + " DETAILS ---");

            names[i] = mybook.getStudentName(scanner);
            ids[i] = mybook.getStudentID(scanner);
            scanner.nextLine(); // clear buffer after ID input

            System.out.print("DO YOU WANT TO ISSUE A BOOK? (yes/no): ");
            String issueChoice = scanner.nextLine();
            if (issueChoice.equalsIgnoreCase("yes")) {
                issuedBooks[i] = mybook.issueBook(scanner);
            } else {
                issuedBooks[i] = "NO BOOK ISSUED";
            }

            System.out.print("DO YOU WANT TO RETURN A BOOK? (yes/no): ");
            String returnChoice = scanner.nextLine();
            if (returnChoice.equalsIgnoreCase("yes")) {
                returnedBooks[i] = mybook.returnBook(scanner);
            } else {
                returnedBooks[i] = "NO BOOK RETURNED";
            }
        }

        // Get purchased book count
        purchasedBooks = mybook.newBooksPurchased(scanner);

        // Final summary report
        mybook.printReport(names, ids, issuedBooks, returnedBooks, purchasedBooks, total);

        scanner.close();
    }
}

// Abstract class with full abstraction
abstract class library {
    public abstract String getStudentName(Scanner sc);
    public abstract int getStudentID(Scanner sc);
    public abstract String issueBook(Scanner sc);
    public abstract String returnBook(Scanner sc);
    public abstract int newBooksPurchased(Scanner sc);
    public abstract void printReport(String[] names, int[] ids, String[] issueBooks, String[] returnBooks, int purchased, int total);
}

//Subclass implementing all abstract methods
class mylibrary extends library {

    @Override
    public String getStudentName(Scanner sc) {
        System.out.print("ENTER STUDENT NAME: ");
        return sc.nextLine();
    }

    @Override
    public int getStudentID(Scanner sc) {
        System.out.print("ENTER STUDENT ID: ");
        return sc.nextInt();
    }

    @Override
    public String issueBook(Scanner sc) {
        System.out.print("ENTER BOOK NAME TO ISSUE: ");
        return sc.nextLine();
    }

    @Override
    public String returnBook(Scanner sc) {
        System.out.print("ENTER BOOK NAME TO RETURN: ");
        return sc.nextLine();
    }

    @Override
    public int newBooksPurchased(Scanner sc) {
        System.out.print("\nHOW MANY BOOKS PURCHASED THIS MONTH? ");
        return sc.nextInt();
    }

    @Override
    public void printReport(String[] names, int[] ids, String[] issueBooks, String[] returnBooks, int purchased, int total) {
        System.out.println("\n======== FINAL LIBRARY REPORT ========");
        for (int i = 0; i < total; i++) {
            System.out.println("\nSTUDENT " + (i + 1));
            System.out.println("NAME            : " + names[i]);
            System.out.println("ID              : " + ids[i]);
            System.out.println("ISSUED BOOK     : " + issueBooks[i]);
            System.out.println("RETURNED BOOK   : " + returnBooks[i]);
        }
        System.out.println("\nTOTAL BOOKS PURCHASED THIS MONTH: " + purchased);
    }
}