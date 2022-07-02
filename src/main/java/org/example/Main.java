package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Welcome To E-Banking");
            System.out.println("--------------------");
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.println("Please Select An Operation");
            String Choice = input.next();
            switch(Choice){
                case "1":
                    Customer customer = new Customer();
                    break;
                case "2":
                    Staff staff = new Staff();
                    break;
                case "3":
                    System.out.println("Thank You For Using E-Banking");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Enter A Valid Selection");
            }
        }while(true);
    }
}