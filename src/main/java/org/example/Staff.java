package org.example;

import java.io.*;
import java.util.Scanner;
import java.io.BufferedReader;

public class Staff{
    private String username;
    private String password;
    private String staffID;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }


    Scanner input = new Scanner(System.in);
    Staff(){
        int loop = 1;
        do {
            System.out.println("1. Login");
            System.out.println("2. Forgot Password");
            System.out.println("3. Back");
            System.out.println("4. Exit");
            String Choice = input.next();

            switch (Choice){
                case "1":
                    auth.Login();
                    break;
                case "2":
                    auth.forgotPassword();
                    break;
                case "3":
                    System.out.println("This is Back");
                    loop =2;
                    break;
                case "4":
                    System.out.println("Thank You");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please Select an Correct Option");
            }
        }while (loop == 1);
    }

    Authentication auth = new Authentication() {
        @Override
        int Login() {
            int loop = 1;
            do {
                File AdminDetailsPath = new File("admin.txt");
                try{
                    Scanner readFile = new Scanner(AdminDetailsPath);
                    System.out.println("Please Enter Your user Name");
                    String useName = input.next();
                    setUsername(useName);
                    System.out.println("Please Enter Your Password");
                    String password = input.next();
                    while (readFile.hasNextLine()){
                        String data = readFile.nextLine();
                        String[] Data = data.split(",");
                        if(
                                Data[0].equals("userName-"+useName) &&
                                Data[1].equals("password-"+password)
                        ){
                            StaffSession staffSession = new StaffSession(getUsername());
                            loop = 0;
                        }else {
                            continue;
                        }
                    }
                }catch(FileNotFoundException e){
                    System.out.println("Admin Details File Not Fount");
                }
            }while(loop == 1);
            return 0;
        }

        @Override
        int forgotPassword() {
            File file = new File("admin.txt");
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
}
