// Name: Kant Tantasathien


import java.util.ArrayList;

public class BookshelfTester{
    private static void testEx2(){
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
    private static void testEx3(){
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
    
    public static void testEx4(){
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
    
    public static void main(String[] args){
        System.out.println("Ex2 ");
        testEx2();
        System.out.println("Ex3 ");
        testEx3();
        System.out.println("Ex4 ");
        testEx4();
    }
 }