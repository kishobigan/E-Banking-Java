package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class StaffSession {
    Scanner input = new Scanner(System.in);
    StaffSession(String userName){
        int loop = 1;
        do {
            System.out.println("Welcome "+userName);
            System.out.println("1. Add Users");
            System.out.println("2. Add Admins");
            System.out.println("3. Logout");
            System.out.println("4. Exit");
            String Choice = input.next();

            switch (Choice) {
                case "1":
                    addUser();
                    break;
                case "2":
                    addAdmin();
                    break;
                case "3":
                    System.out.println("Thank you");
                    loop =0;
                    break;
                case "4":
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Select A valid Selection");
            }
        }while (loop == 1);
    }

    int addUser(){
        File userFilePath = new File("users.txt");
        Scanner sc = null;
        FileWriter writer = null;
        String oldContent = "";
        String newContent = "";
        try{
            System.out.println("Enter User's user Name");
            String userName = input.next();
            System.out.println("Enter User's Account Number");
            String accNo = input.next();
            System.out.println("Enter User's intial Amount");
            String intialAmount = input.next();
            System.out.println("Enter User's password");
            String password = input.next();
            sc = new Scanner(userFilePath);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                oldContent = oldContent+line+System.lineSeparator();
            }
            newContent = oldContent+"userName-"+userName+",password-"+password+","+accNo;
            writer = new FileWriter(userFilePath);
            File newFile = new File("users/"+accNo+".txt");
            newFile.createNewFile();
            FileWriter newFileWrite = new FileWriter(newFile);
            newFileWrite.write("Your Available Balance is "+intialAmount);
            newFileWrite.close();
            writer.write(newContent);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
    int addAdmin() {
        File userFilePath = new File("admin.txt");
        Scanner sc = null;
        FileWriter writer = null;
        String oldContent = "";
        String newContent = "";
        try{
            System.out.println("Enter Admin's user Name");
            String userName = input.next();
            System.out.println("Enter Admin's password");
            String password = input.next();
            sc = new Scanner(userFilePath);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                oldContent = oldContent+line+System.lineSeparator();
            }
            newContent = oldContent+"userName-"+userName+",password-"+password;
            writer = new FileWriter(userFilePath);
            writer.write(newContent);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
