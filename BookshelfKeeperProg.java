// Name: Kant Tantasathien

import java.util.ArrayList;
import java.util.Scanner;

class BookshelfKeeperProg {
    /**
     * This is a helper method that tests out the constructor in Bookshelf's
     * constructor for exercise 2 of lab5.
     */
    private static void testEx2() {
        ArrayList<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(3);
        books.add(5);
        books.add(7);

        Bookshelf bs1 = new Bookshelf();
        Bookshelf bs2 = new Bookshelf(books);

        System.out.print("Default Constructor: ");
        System.out.println(bs1.toString());

        System.out.print("Parameter Constructor: ");
        System.out.println(bs2.toString());

        System.out.println();
    }

    /**
     * This is a helper method that tests out the Bookshelf's meethod for exercise 3
     * of lab5.
     */
    private static void testEx3() {
        ArrayList<Integer> books = new ArrayList<>();
        books.add(4);
        books.add(5);
        books.add(6);
        books.add(7);

        Bookshelf bs1 = new Bookshelf(books);

        System.out.print("Base Value: ");
        System.out.println(bs1.toString());

        bs1.addFront(3);
        System.out.print("Add Front(3): ");
        System.out.println(bs1.toString());

        bs1.addLast(8);
        System.out.print("Add Last(8): ");
        System.out.println(bs1.toString());

        bs1.removeFront();
        System.out.print("Remove Front: ");
        System.out.println(bs1.toString());

        bs1.removeLast();
        System.out.print("Remove Last: ");
        System.out.println(bs1.toString());

        System.out.println();
    }

    /**
     * This is a helper method that tests out the rest of Bookshelf's meethod for
     * exercise 4 of lab5.
     */
    private static void testEx4() {
        ArrayList<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        books.add(3);
        books.add(4);

        Bookshelf bs1 = new Bookshelf(books);

        System.out.print("Base Value: ");
        System.out.println(bs1.toString());

        System.out.print("Expected BookShelf Size(4): ");
        System.out.println(bs1.size());
        bs1.addLast(5);
        System.out.print("Expected BookShelf Size(5): ");
        System.out.println(bs1.size());

        System.out.print("Expected Height of Third Book(3):  ");
        System.out.println(bs1.getHeight(2));

        System.out.print("Expected Sorted To Be true:  ");
        System.out.println(bs1.isSorted());

        bs1.addLast(2);
        System.out.print("Expected Sorted To Be false:  ");
        System.out.println(bs1.isSorted());

        System.out.println();

    }

    /**
     * This is a helper method that tests out all the methods for TestKeeper.
     */
    private static void testKeeper() {
        ArrayList<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(2);
        books.add(3);
        books.add(5);
        books.add(7);

        Bookshelf bs1 = new Bookshelf(books);
        BookshelfKeeper testRun = new BookshelfKeeper(bs1);

        System.out.print("Base Value: ");
        System.out.println(testRun.toString());

        int operations = testRun.pickPos(1);
        System.out.printf("Expected number of operations is 3 : %d\n", operations);
        System.out.print("After pickPos(1): ");
        System.out.println(testRun.toString());

        operations = testRun.putHeight(4);
        System.out.printf("Expected number of operations is 5 : %d\n", operations);
        System.out.print("After putHeight(4): ");
        System.out.println(testRun.toString());
    }

    /**
     * This is a helper method that takes in a string of Book's Height and produces
     * elements of book for the bookshelf from that height. It also prints out the
     * error and exit program when the error encountered.
     */
    private static Bookshelf createBookshelf(String input) {
        String[] splitLine = input.split("\\s+"); // Find all of the spaces in a row and treat it as a single split
                                                  // point.
        Bookshelf shelf = new Bookshelf();

        if (input.isEmpty()) { // ifEmpty returns true if and onlyf if length is 0.
            return shelf;
        }
        int value;
        int oldValue = -1;
        for (String token : splitLine) { // for every string in the array
            value = Integer.parseInt(token); // this allows the computer to regcognize any String of numeric characters
                                             // as one number.
            if (value < 0) {
                System.out.println("ERROR: Height of a book must be positive.");
                return null;
            }
            if (value < oldValue) {
                System.out.println("ERROR: Heights must be specified in non-decreasing order.");
                return null;
            }

            shelf.addLast(value);
            oldValue = value;
        }
        return shelf;
    }

    /**
     * This is a helper method that processes user Actions (Pick, Put, End) It also
     * prints out messages identifying the errors and exit the program when
     * recieving such errors.
     */
    private static boolean performAction(String line, BookshelfKeeper keeper) {
        int value = 0;
        String[] action = line.split("\\s+"); // all the spaces inbetween the numbers entered will be treated as a
                                              // single space
        switch (action[0]) {
            case "pick":
                value = Integer.parseInt(action[1]);
                if (value >= keeper.getNumBooks() || value < 0) {
                    System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                    return false;
                }
                keeper.pickPos(value);
                break;

            case "put":
                value = Integer.parseInt(action[1]); // parse
                if (value < 0) {
                    System.out.println("ERROR: Height of a book must be positive.");
                    return false;
                }
                keeper.putHeight(value);
                break;

            case "end":
                return false;

            default:
                System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
                return false;
        }
        return true;
    }
    /**
     * This is the main entry point, retrieves user input 
     * prints out messages identifying the errors and exit the program when
     * recieving such errors. It instantiates, Bookshelf and Bookshelf Keeper.
     * The method also calls the helper static function createBookshelf to create a bookshelf from the user input.
     * The method also calls the helper static function peformAction to process the user's command/action. 
     */
    public static void main(String[] args) {

        // Below is the unit test, uncomment it to test
        // System.out.println("Ex2 ");
        // testEx2();
        // System.out.println("Ex3 ");
        // testEx3();
        // System.out.println("Ex4 ");
        // testEx4();
        // testKeeper();

        Scanner input = new Scanner(System.in);
        String line;
        System.out.println("Please enter initial arrangement of books followed by newline:");
        line = input.nextLine().trim(); // retrieve userInput and strip all white space before the beginning number and
                                        // after the end number.
        Bookshelf shelf = createBookshelf(line); // passing input in for processing into a shelf.
        if (shelf == null) {
            System.out.println("Exiting Program."); // input was invalid.
            input.close(); // cleaning up scanner
            return;
        }

        BookshelfKeeper bookKeeper = new BookshelfKeeper(shelf);
        System.out.println(bookKeeper.toString()); // every time I perform an action, the ArrayList that is inside the
                                                   // bookshelf will be printed out.

        System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
        // Looping forever until perform action is false
        while (true) { // The program will keep asking for answers until an error is encountered or
                       // when "end" is input.
            line = input.nextLine().trim(); // retrieve userInput and strip all white space from the ends of the line.

            if (!performAction(line, bookKeeper)) { // passing input in for processing
                System.out.println("Exiting Program."); // system is broken. Either an error was found or end was typed
                                                        // in.
                break;
            }
            System.out.println(bookKeeper.toString()); // outputing the bookkeeper.
        }
        input.close(); // cleaning up scanner
    }
}