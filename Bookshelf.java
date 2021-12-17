// Name: Kant Tantasathien

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.   
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/

import java.util.ArrayList;

public class Bookshelf {

    /**
      Representation invariant: 

      1. Each element in pile of books need to be greater than 0
      2. Bookshelf can't be null
      
   */
   
   // <add instance variables here> 
   private ArrayList<Integer> pileOfBooks;
   /**
    * Creates an empty Bookshelf object i.e. with no books   
    */
   public Bookshelf() {      //I don't know how to implement this constructor
      pileOfBooks = new ArrayList<Integer>();
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    * 
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.pileOfBooks = new ArrayList<Integer>(pileOfBooks);
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      
      pileOfBooks.add(0,height);    //0 is the front
      assert isValidBookshelf();
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    * 
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      
      pileOfBooks.add(height);   //if we don't put anything in front, it'll just start at the end
      assert isValidBookshelf();
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int front = pileOfBooks.get(0);
      pileOfBooks.remove(0);
      assert isValidBookshelf();
      return front;  
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    * 
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {

      int lastId = pileOfBooks.size() - 1;
      int last = pileOfBooks.get(lastId);
      pileOfBooks.remove(lastId);
      assert isValidBookshelf();
      return last; 
   }

   /*
    * Gets the height of the book at the given position.
    * 
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      int height = pileOfBooks.get(position);
      assert isValidBookshelf();
      return height;   
      
   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      
      assert isValidBookshelf();
      return pileOfBooks.size();   

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      assert isValidBookshelf();
      return pileOfBooks.toString();
      
   }
   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      assert isValidBookshelf();
      if(pileOfBooks.size() == 0){
         return true;
      }
      int left = pileOfBooks.get(0);
      for(int i = 1; i<pileOfBooks.size();i++){
         if(left > pileOfBooks.get(i)){    //Left is suppsoed to be more than right if things are sorted. If it's not, then it's not sorted hence, false
            return false;
         }
         left = pileOfBooks.get(i);
      }
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      if(pileOfBooks == null){
         return false;
      }
      for(int i = 0; i < pileOfBooks.size();i++){
         if(pileOfBooks.get(i) <= 0){
            return false;
         }
      }
      return true;
   }

} 
