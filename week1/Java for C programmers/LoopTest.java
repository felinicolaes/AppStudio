public class LoopTest {
   // The main method is the point of entry into the program...
   public static void main(String[] args) {
      LoopTest me = new LoopTest();
      me.doStuff();
   }
   // add your code here...
   public void doStuff() {
      System.out.println("power of 2, A: " + powerOf2A(0));
      System.out.println("power of 2, B: " + powerOf2B(0));
      System.out.println("power of 2, C: " + powerOf2C(0));

      System.out.print("regel van lengte 6, A: "); printLineA(6);
      System.out.print("regel van lengte 6, B: "); printLineB(6);
      System.out.print("regel van lengte 6, C: "); printLineC(6);
   }

   // These functions compute powers of two.
   public int powerOf2A(int n) {
      int counter = n;
      int result  = 1;
      while (counter != 0) {
         result = 2 * result;
         counter--;
      }
      return result;
   }

   public int powerOf2B(int n) {
      int counter = n;
      int result  = 1;
      if (n == 0)
         return result;
      do {
         result = 2 * result;
         counter--;
      } while (counter != 0);
      return result;
   }

   public int powerOf2C(int n) {
      int counter;
      int result;
      for (counter = n, result = 1; counter != 0; counter--) {
         result = 2 * result;
      }
      return result;
   }

   /**
     *  Prints a row of stars of a given length.
     */

   public void printLineA(int length) {
      while (length != 0) {
         System.out.print("#");
         length--;
      }
      System.out.println();
   }

   public void printLineB(int length) {
      do {
         System.out.print("#");
         length--;
      } while (length != 0);
      System.out.println();
   }

   public void printLineC(int length) {
      for (int i=0; i<length; i++) {
         System.out.print("#");
      }
      System.out.println();
   }
}
