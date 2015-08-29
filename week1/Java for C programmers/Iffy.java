public class Iffy {
   private int anIntProp = 42;
   public static void main(String[] args) {
      Iffy me = new Iffy();
      me.callSomeMethods();
   }

   public void callSomeMethods() {
      warnIfNegative(2);
      warnIfNegative(-3);
      warnIfNegative(0);

      resetIfNegative(2);
      resetIfNegative(-3);
      resetIfNegative(0);      

      System.out.println("Is 3 in range between 5 and 6? " + isInRange(3,6,5));
      System.out.println("Is 3 in range between 6 and 6? " + isInRange(3,6,6));
      System.out.println("Is 3 in range between 2 and 6? " + isInRangeIfLess(3,6,2));
   }

   /**
    * Print out a warning if the value is negative
    * otherwise don't do anything.
    */
   // Used to be that all numbers resulted in a warning, because curly brackets were
   // not used
   public void warnIfNegative(int theValue) {
      if(theValue < 0) {
        System.out.println("Caution - negative value given (" + theValue + ")");
      }
   }
  
   /**
    * Print out a warning if the value of anIntProp is negative
    * and also set the value to zero.
    * Otherwise don't do anything.
    */
   // If statement was not properly wrapped and anIntProp could not be given to method
   public void resetIfNegative(int anIntProp) {
      if(anIntProp < 0) {
        System.out.println("Caution - negative value given (" + anIntProp + "), value resetted");
        anIntProp = 0;
      }
   }

   /**
    * return true if value is between upperBound and LowerBound
    * (or equal to either bound) otherwise return false.
    */
  public boolean isInRange(int value, int upperBound, int lowerBound) {
    if (upperBound <= lowerBound)
      System.out.println("upperBound is less or equal to the lower bound:");
    if(lowerBound <= value && value <= upperBound )
      return true;
    else
      return false;
  }

  public boolean isInRangeIfLess(int value, int upperBound, int lowerBound) {
    return (lowerBound <= value && value <= upperBound);
  }
}
