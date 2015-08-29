class Human {

   // Properties of the class...
   String name;

   // Constructor of the class...
   public Human(String aName) {
      name = aName;
   }

   // Methods of the class...
   public String toString() {
      return "I am a human and my name is " + name;
   }
}

class ArrayTest2 {

   public static void main(String[] args) {
      ArrayTest2 x = new ArrayTest2();
      x.doStuff();
   }

   public void doStuff() {
      Human[] threesome = new Human[3];

      Human angus = new Human("Angus");
      Human brian = new Human("Brian");
      Human charles = new Human("Charles");

      threesome[0] = angus;
      threesome[1] = brian;
      threesome[2] = charles;
        
      printArray(threesome);
   }

   public void printArray(Human[] anArray) {
      for (int i=0; i<anArray.length; i++) {
         System.out.println(anArray[i].toString());
      }
   }   
}
