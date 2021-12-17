// Name: Kant Tantasathien

/**
 * Class BookshelfKeeper 
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in 
 * non-decreasing order by height, with the restriction that single books can only be added 
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put 
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
import java.util.ArrayList;

class BookshelfKeeper {
   /**
    * Representation invariant:
    * 
    * 1. Sorted bookshelf need to be in an ascending order. 
    * 2. The bookshelf can't be null
    * 
    */

   // <add instance variables here>
   private Bookshelf sortedBookshelf;
   private int operationsCount;
   private int latestOperationCount;

   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   BookshelfKeeper() {
      sortedBookshelf = new Bookshelf();
      operationsCount = 0;
      latestOperationCount = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Initializes sortedBookShelf, operationCount and latestOperationCount Note:
    * method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   BookshelfKeeper(Bookshelf sortedBookshelf) {
      this.sortedBookshelf = sortedBookshelf;
      operationsCount = 0;
      latestOperationCount = 0;
      assert isValidBookshelfKeeper();
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps
    * bookshelf sorted after picking up the book.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: 0 <= position < getNumBooks()
    */
   public int pickPos(int position) {
      int backloc = sortedBookshelf.size() - 1; // This represents the location of the last element.
      if ((position) <= (backloc - position)) { // If it's closer to the front, or if the distance is equal, remove
                                                // front
         assert isValidBookshelfKeeper();
         return getFromFront(position); // remove front via the helper method
      }
      assert isValidBookshelfKeeper();
      return getFromBack(position); // if the intial condidtion did not met, rem
   }

   /**
    * Inserts book with specified height into the shelf. Keeps the contained
    * bookshelf sorted after the insertion.
    * 
    * Returns the number of calls to mutators on the contained bookshelf used to
    * complete this operation. This must be the minimum number to complete the
    * operation.
    * 
    * PRE: height > 0
    */
   public int putHeight(int height) {
      int leftPosition = 0;
      int rightPosition = 0;

      for (int i = 0; i < sortedBookshelf.size(); i++) {
         if (sortedBookshelf.getHeight(i) >= height) {
            break;
         }
         leftPosition++;
      }

      for (int i = sortedBookshelf.size() - 1; i >= 0; i--) {
         if (sortedBookshelf.getHeight(i) <= height) {
            break;
         }
         rightPosition++;
      }

      if (leftPosition <= rightPosition) { // If it's closer to the front, or if the distance is equal, remove front
         assert isValidBookshelfKeeper();
         return putFromFront(leftPosition, height); // Insert from the front if the location is closer to the front.
      }
      assert isValidBookshelfKeeper();
      return putFromBack(rightPosition, height); // Insert from the back if the height is closer to the back
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper();
      return operationsCount;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper();
      return sortedBookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String
    * containing height of all books present in the bookshelf in the order they are
    * on the bookshelf, followed by the number of bookshelf mutator calls made to
    * perform the last pick or put operation, followed by the total number of such
    * calls made since we created this BookshelfKeeper.
    * 
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    * 
    */
   public String toString() {
      assert isValidBookshelfKeeper();
      return String.format("%s %d %d", sortedBookshelf.toString(), latestOperationCount, operationsCount);                                                                                               
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state. (See
    * representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {

      if (sortedBookshelf == null) {
         return false;
      }

      return sortedBookshelf.isSorted(); // dummy code to get stub to compile

   }

   // add any other private methods here
      /**
    * This is a helper method for putHeight reserved for the scenario in which the
    * position of the book is closer to the front.
    */
   private int putFromFront(int position, int height) {
      int count = 0;
      ArrayList<Integer> cart = new ArrayList<>(); // cart is used to keep track of the items that need to be put back
                                                   // into the shelf after the "put".
      for (int i = 0; i < position; i++) {
         cart.add(sortedBookshelf.getHeight(0)); // remove everything in front of where the new book should be located
                                                 // at
         sortedBookshelf.removeFront();
         count++;
      }
      sortedBookshelf.addFront(height); // add the book in at correct positon in sortedBookShelf
      count++;
      for (int j = cart.size() - 1; j >= 0; j--) {
         sortedBookshelf.addFront(cart.get(j)); // added in the book that was removed before
         count++;
      }
      operationsCount += count;
      latestOperationCount = count;
      assert isValidBookshelfKeeper();
      return count;
   }
   /**
    * This is a helper method for putHeight reserved for the scenario in which the
    * position of the book is closer to the back.
    */
   private int putFromBack(int position, int height) {
      int count = 0;
      int target = sortedBookshelf.size() - position;
      ArrayList<Integer> cart = new ArrayList<>();
      for (int i = sortedBookshelf.size() - 1; i >= target; i--) { // initial remove - first need to transfer evertyhing
                                                                   // to bookshelf, may need to keep track of frontLoc
         cart.add(sortedBookshelf.getHeight(i));
         sortedBookshelf.removeLast();
         count++;
      }
      sortedBookshelf.addLast(height);
      count++;
      for (int j = cart.size() - 1; j >= 0; j--) {
         sortedBookshelf.addLast(cart.get(j));
         count++;
      }
      operationsCount += count;
      latestOperationCount = count;
      assert isValidBookshelfKeeper();
      return count;
   }

      /**
    * This is a helper method for pickPos reserved for the scenario in which the
    * position of the book is closer to the front.
    */
   private int getFromFront(int position) {
      int count = 0;
      ArrayList<Integer> cart = new ArrayList<>(); // cart is used to keep track of the items that need to be put back
                                                   // into the shelf after the pick.
      for (int i = 0; i < position; i++) { // initial remove. This for Loop removes all books prior to the book that the
                                           // user wishes to pick.
         cart.add(sortedBookshelf.getHeight(0)); // The positon of the book at the front will always be at position 0.
         sortedBookshelf.removeFront();
         count++; // This will be incremented every time a book has been remove
      }
      sortedBookshelf.removeFront(); // This would pick of the book at the postion in the parameter
      count++;
      for (int j = 1; j <= cart.size(); j++) { // this for loop adds the book in the cart back to the sortedBookShelf
         sortedBookshelf.addFront(cart.get(cart.size() - j));
         count++;
      }
      operationsCount += count; // Operations count would tally up all of the operations done in this method.
      latestOperationCount = count;
      assert isValidBookshelfKeeper();
      return count;
   }

   /**
    * This is a helper method for pickPos reserved for the scenario in which the
    * position of the book is closer to the back.
    */
   private int getFromBack(int position) {
      int count = 0;
      ArrayList<Integer> cart = new ArrayList<>(); // cart is used to keep track of the items that need to be put back
                                                   // into the shelf after the pick.
      for (int i = sortedBookshelf.size() - 1; i > position; i--) { // This for loop starts at the end of the
                                                                    // sortedBookShelf
         cart.add(sortedBookshelf.getHeight(i)); // "i" will always be at the last position of the sortedBookShelf
         sortedBookshelf.removeLast(); // remove all of the books and add everything to the cart before the positon of
                                       // the book in Paremeter.
         count++;
      }
      sortedBookshelf.removeLast(); // remove out the book in the parameter.
      count++;
      for (int j = 1; j <= cart.size(); j++) {
         sortedBookshelf.addLast(cart.get(cart.size() - j));
         count++;
      }
      operationsCount += count;
      latestOperationCount = count;
      assert isValidBookshelfKeeper();
      return count;
   }

}
