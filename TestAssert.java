// Name: Kant Tantasathien

import java.util.ArrayList;

public class TestAssert {
    public static void main(String[] args){
        ArrayList<Integer> books = new ArrayList<>();
        books.add(1);
        books.add(3);
        books.add(5);
        books.add(7);
        //books.add(-1);

        Bookshelf bs1 = new Bookshelf(books);
        Bookshelf bs2 = new Bookshelf();

        //bs1.addFront(-1);
        //bs1.addLast(-1);

        //bs2.removeFront();
        //bs2.removeLast();


    }
}
