class Animal {

   // Properties of the class...
   public int     numberOfLegs;
   public boolean hasWings;

   // Constructor of the class...
   public Animal() {
      numberOfLegs = 4;
      hasWings = false;
   }

   // Methods of the class...
   public void talk() {
      System.out.println("Hello");
   }
}

class Bird extends Animal {

   // Properties of the class...
   public boolean canFly;

   // Constructor of the class...
   public Bird() {
      numberOfLegs = 2;
      hasWings = true;
      canFly = true;
   }

   // Methods of the class...
   public void fly() {
      System.out.println("flap flap");
   }
}

class Eagle extends Bird {

   // Properties of the class...
   public int numberOfKills; //needs to be public if accessed from other class

   // Constructor of the class...
   Eagle() {
      numberOfKills = 0;
   }

   // Methods of the class...
   public void attack() {
      numberOfKills++;
   }
}

class AnimalTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Animal a = new Animal();
      System.out.println(a.numberOfLegs);
      System.out.println(a.hasWings);
      a.talk();
      //a.fly(); // only birds can fly and not all animals are birds

      Bird b = new Bird();
      System.out.println(b.numberOfLegs);
      System.out.println(b.hasWings);
      System.out.println(b.canFly);
      //System.out.println(b.numberOfKills); // only eagles can kill and not all eagles are birds
      b.talk();
      //b.attack(); // only eagles can attack and not all eagles are birds

      Eagle e = new Eagle();
      System.out.println(e.numberOfKills);
      System.out.println(e.numberOfLegs); // is 2 because an eagle is a bird
      System.out.println(e.hasWings);
      e.talk();
      e.attack();

      a=b;
      a.talk();
      //a.fly(); //animal still cannot fly

      //b=a; // animal is not always bird
      b.talk();
      b.fly();
   }
}
