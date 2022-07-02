package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
                    auth.Login();
                    break;
                case "2":
                    auth.forgotPassword();
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
    Authentication auth = new Authentication() {
        @Override
        int Login() {
            int loop = 1;
            do {
                File AdminDetailsPath = new File("users.txt");
                try{
                    Scanner readFile = new Scanner(AdminDetailsPath);
                    System.out.println("Please Enter Your user Name");
                    String useName = input.next();
                    setUserName(useName);
                    System.out.println("Please Enter Your Password");
                    String password = input.next();
                    while (readFile.hasNextLine()){
                        String data = readFile.nextLine();
                        String[] Data = data.split(",");
                        if(
                                Data[0].equals("userName-"+useName) &&
                                Data[1].equals("password-"+password)
                        ){
                            setAccNumber(Data[2]);
                            CustomerSession customerSession = new CustomerSession(getUserName(),getAccNumber());
                            loop = 0;
                        }else {
                            System.out.println("User name password incorrect");
                            continue;
                        }
                    }
                }catch(FileNotFoundException e){
                    System.out.println("user Details File Not Fount");
                }
            }while(loop == 1);
            return 0;
        }

        @Override
        int forgotPassword() {
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
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }
}
