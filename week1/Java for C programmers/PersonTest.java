class Person {

   // Properties of the class...
   private String name;
   public int    age;
   private String birthplace;
    
   // Constructor of the class...
   public Person(String aName, int anAge, String aBirthplace) {
      name = aName;
      age  = anAge;
      birthplace = aBirthplace;
   }

   /*
   Person ls = new Person();
   Person wp = new Person();
   */

   // Methods of the class...
   public void talk() {
      System.out.println("Hi, my name's " + name);
      System.out.println("and my age is " + age);
      System.out.println("and I was born in " + birthplace);
      System.out.println();
   }
   public void commentAboutAge() {
      commentAboutAge();

      if (age < 5) {
         System.out.println("baby");
      }
      if (age == 5) {
         System.out.println("time to start school");
      }
      if (age > 60) {
         System.out.println("Old person");
      }
   }

   public void growOlder() {
      age = age + 1;
   }

   public void growOlderBy(int ageAdded) {
      age = age + ageAdded;
   }

   public void giveKnighthood() {
      name = "Sir " + name;
   }
}

class PersonTest {

   // The main method is the point of entry into the program...
   public static void main(String[] args) {

      Person ls = new Person("Luke Skywalker",34,"Polis Massa");
      Person wp = new Person("Winston Peters",48,"New Zealand");

      ls.growOlderBy(10);
      ls.giveKnighthood();
      ls.talk();

      wp.growOlder();
      wp.talk();

      System.out.print("Luke's age is " + ls.age);
   }

}

