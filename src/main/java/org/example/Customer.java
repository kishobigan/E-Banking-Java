package org.example;

import java.util.Scanner;

public class Customer {
    private String userName;
    private String password;
    private String accNumber;
    Scanner input = new Scanner(System.in);
    Customer(){
        int loop = 1;
        do {
            System.out.println("1. Login");
            System.out.println("2. Forgot Password");
            System.out.println("3. Back");
            System.out.println("4. Exit");
            System.out.println("Please Select An Operation");
            String Choice = input.next();

            switch (Choice){
                case "1":
                    System.out.println("This is Login");
                    break;
                case "2":
                    System.out.println("This is Forget Password");
                    break;
                case "3":
                    loop = 0;
                    break;
                case "4":
                    System.out.println("Thank You using E-Banking");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Enter an Valid Choice");
            }
        }while (loop == 1);
    }
}
