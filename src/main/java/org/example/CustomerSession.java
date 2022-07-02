package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CustomerSession {
    Scanner input = new Scanner(System.in);
    CustomerSession(String userName, String accNo){
        int loop = 0;
        do {
            System.out.println("Hello "+ userName);
            System.out.println("1. Check Balance");
            System.out.println("2. Transfer Amount");
            System.out.println("3. Transaction History");
            System.out.println("4. Change Password");
            System.out.println("5. Logout");
            System.out.println("6. Exit");
            String Choice = input.next();

            switch (Choice){
                case "1":
                    System.out.println(checkBalance(accNo));
                    break;
               case "2":
                    transferAmount(accNo);
                    break;
               case "3":
                    paymentHistory(accNo);
                    break;
               case "4":
                    changePassword();
                    break;
               case "5":
                    System.out.println("Bye "+userName);
                    loop = 1;
                    break;
               case "6":
                    System.out.println("Thank You For Using E-Banking");
                   System.exit(0);
                    break;
                default:
                    System.out.println("Please Select Correct Option");

            }
        }while (loop != 1);
    }

    String checkBalance(String accNo){
        File usersFilePath = new File("users/"+accNo+".txt");
        String line = "";
        try {
            Scanner sc = new Scanner(usersFilePath);
            while (sc.hasNextLine()){
                line = sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
    int transferAmount(String accNo){
        File senderFilePath = new File("users/"+accNo+".txt");
        System.out.println("Please Enter The Receiver Account Number : ");
        String receiverAccNo = input.next();
        System.out.println("Please Enter Amount You Want Transfer : ");
        float amount = input.nextFloat();
        File receiverFilePath = new File("users/"+receiverAccNo+".txt");
        float senderBalance = getBalance(checkBalance(accNo)) - amount;
        float receiverBalance = getBalance(checkBalance(receiverAccNo)) + amount;
        String senderDetails = "";
        String receiverDetails = "";
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = now.format(format);
        try{
            Scanner senderFileScan = new Scanner(senderFilePath);
            Scanner receiverFileScan = new Scanner(receiverFilePath);
            while(senderFileScan.hasNextLine()){
                senderDetails += senderFileScan.nextLine()+System.lineSeparator();
            }
            while(receiverFileScan.hasNextLine()){
                receiverDetails += receiverFileScan.nextLine()+System.lineSeparator();
            }
            String senderFinalDetails = senderDetails +formatDateTime +" You Have sent cash "+amount+" to "+receiverAccNo+"\nYour Available Balance is "+senderBalance;
            String receiverFinalDetails = receiverDetails +formatDateTime +" You Have Got cash "+amount+" from "+accNo+"\nYour Available Balance is "+receiverBalance;
            FileWriter senderFileWriting = new FileWriter(senderFilePath);
            FileWriter receiverFileWriting = new FileWriter(receiverFilePath);
            senderFileWriting.write(senderFinalDetails);
            receiverFileWriting.write(receiverFinalDetails);
            senderFileWriting.close();
            receiverFileWriting.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    float getBalance(String balanceDetails){
        float Balance;
        String[] balance = balanceDetails.split(" ");
        Balance = Float.parseFloat(balance[4]);
        return Balance;
    }
    int paymentHistory(String accNo){
        File accountFilePath = new File("users/"+accNo+".txt");
        try {
            Scanner userFileScan = new Scanner(accountFilePath);
            while (userFileScan.hasNextLine()){
                String line = userFileScan.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    int changePassword(){
        File file = new File("users.txt");
        Scanner sc = null;
        FileWriter writer = null;
        String oldContent = "";
        System.out.println("Please Enter Your user name");
        String userName = input.next();
        String oldOne = "";
        String newOne = "";
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                if(line != null){
                    oldContent = oldContent+line+System.lineSeparator();
                    String[] Data = line.split(",");
                    if(Data[0].equals("userName-"+userName)){
                        String oldPassword = Data[1];
                        System.out.println("Please enter Your New Password");
                        String newPassword = input.next();
                        System.out.println("Please Re-enter Your New Password");
                        String confirmPassword = input.next();
                        if (newPassword.equals(confirmPassword)){
                            oldOne = Data[0]+","+Data[1];
                            newOne = Data[0]+","+"password-"+newPassword;
                        }
                        else{
                            System.out.println("Your Passwords Does Not Matched");
                        }
                    }
                }
            }
            String newContent = oldContent.replaceAll(oldOne,newOne);
            writer = new FileWriter(file);
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
