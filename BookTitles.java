import java.util.Scanner;

public class BookTitles {
    public static void main(String[] args) {
        String[] books = {"JW Books", "Physics", "Chemistry", "Biology",
						  "English", "Economics", "Geography", 
						  "Constitution", "Dictionary", "Mathematics"};

        System.out.println("List of books:");
        for (String book : books) {
            System.out.println(book);
        }

        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter a book name (Please note case sensitive): ");
        String search = input.nextLine();

        boolean found = false;
        for (String b : books) {
            if (b.equals(search)) {
                found = true;
            }
        }

        if (found == true) {
            System.out.printf("%s is available.%n",search);
        } else {
            System.out.printf("%s not found.",search);
        }
    }
}