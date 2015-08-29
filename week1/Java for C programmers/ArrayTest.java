class ArrayTest {

   public static void main(String[] args) {
      ArrayTest x = new ArrayTest();
      x.doStuff();
     
   }

   public void doStuff() {
      // creates the array fred of ten integers.
      int[] fred = new int[10];
      double[] nums = new double[10];

      // sets the values of the fred array
      for (int i=0; i<10; i++) {
         fred[i] = i;
      }

      // prints the values of the fred array
      /* for (int i=0; i<10; i++) {
         System.out.println(fred[i]);
      }
      */
      
      //sets the values of the nums array
      double c = 1.0;
      for (int i=0; i<10; i++) {
         nums[i] = c;
         c = c + 0.1;
      }

      // prints the values of the nums array
      /* for (int i=0; i<10; i++) {
         System.out.println(nums[i]);
      }
      */

      printArray(nums);
   }

   public void printArray(int[] anArray) {
      for (int i=0; i<anArray.length; i++) {
         System.out.println(anArray[i]);
      }
   }

   public void printArray(double[] anArray) {
      for (int i=0; i<anArray.length; i++) {
         System.out.println(anArray[i]);
      }
   }
}
