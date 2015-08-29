/*
 * A class to deposit, withdraw, show balance of a bank account
 */ 

public class SavingsAccount {
   public int balance;

   public SavingsAccount() {
      balance = 0;
   }

   public SavingsAccount(int aBalance) {
      balance = aBalance;
   }

   public void greet() {
      System.out.println("Hello and welcome!");
   }

   public void showBalance() {
      System.out.println("Your balance is " + balance);
   }

   public void deposit(int howMuch) {
      if (howMuch < 0) {
         System.out.println("You probably want to withdraw money instead of deposit. Try again.");
      } else {
         balance = balance + howMuch;
      }
   }

   public void withdraw(int howMuch) {
      if(balance - howMuch < 0) {
         System.out.println("You do not have enough money to do this. Try again.");
      } else if (howMuch < 0) {
         System.out.println("You probably want to deposit money instead of withdraw. Try again.");
      } else {
         balance = balance - howMuch;
      }
   }
}
