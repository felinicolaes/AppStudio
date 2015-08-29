class Flea {
   
   // Properties of the class...
   private String name;
   
   // Constructor of the class...
   public Flea(String aName) {
      name = aName;
   }

   // Methods of the class...
   public String toString() {
      return "I am a flea called " + name;
   }

}

class Dog {

   // Properties of the class...
   private String name;
   private int    age;
   public Flea   dogsFlea;

   // Constructor of the class...
   public Dog(String aName, int anAge, Flea aFlea) {
      name     = aName;
      age      = anAge;
      dogsFlea = aFlea;
   }

   public String toString() {
      return "I am a dog called " + name + " aged " + age + " and I have a flea called " + dogsFlea;
   }

}

class DogOwner {

   // Properties of the class...
   private String name;
   private int    salary;
   public Dog   ownersDog;

   // Constructor of the class...
   public DogOwner(String aName, int aSalary, Dog anOwnersDog) {
      name      = aName;
      salary   = aSalary;
      ownersDog = anOwnersDog;
   }
}


class DogTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {
      Flea p = new Flea("Pop");
      Flea s = new Flea("Squeak");
      Flea z = new Flea("Zip");

      Dog r = new Dog("Rex", 1, p);
      Dog j = new Dog("Jimbo", 2, s);
      Dog f = new Dog("Fido", 3, z);

      DogOwner a = new DogOwner("Angus", 65000, r);
      DogOwner b = new DogOwner("Brian", 55000, j);
      DogOwner c = new DogOwner("Charles", 45000, f);

      System.out.println("The flea of Brians dog says '" + b.ownersDog.dogsFlea.toString() + "'");
   }
}

