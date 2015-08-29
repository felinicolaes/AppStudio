class CartoonCharacter {

   // Properties of the class...
   private String name;
   public String favouriteColour;
   private int    favouriteNumber;
   public static int  characterCount;

   // Constructor of the class...
   public CartoonCharacter(String aName, String aColour, int aNumber) {
      name            = aName;
      favouriteColour = aColour;
      favouriteNumber = aNumber;
      characterCount = characterCount + 1;
   }


   // Methods of the class...
   public void displayMe() {
      System.out.println("Hello, my name is " + name);
      System.out.println("my favourite colour is " + favouriteColour);
      System.out.println("and my favourite number is " + favouriteNumber);
   }
}
