package com.company;

import java.util.Scanner;

/**
 * Created by yulia on 02.10.16.
 */
public class UserConnection {
    static String[] arrUserInput = {"", "", ""};
    static String[] wrightInputData = {"sqlcmd", "postgres", "yes"};

    public boolean compareDataConnection() {
        String wrongStatement = "";

        for (int i = 0; i < arrUserInput.length; i++) {
            if(!arrUserInput[i].equals(wrightInputData[i])){
                switch (i){
                    case 0: wrongStatement = "name of database";
                        break;
                    case 1: wrongStatement = "username";
                        break;
                    case 2: wrongStatement = "password";
                        break;
                }
                System.out.println("Sorry, but you input incorrect data in " + wrongStatement + ". Please, try again enter " + wrongStatement);
                Scanner sc = new Scanner(System.in);
                arrUserInput[i] = sc.next();
                compareDataConnection();
            }
        }
        return true;
    }

    public String[] welcomeFromDatabase() {
        System.out.println("Hi, I'm Database manager! Please, wright name of database in which you want to work, username and password in format: nameOfDataBase|username|password ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String[] strArr = input.split("|") ;
        int k = 0;
        for (int i = 0; i < strArr.length; i++) {

            if(!strArr[i].equals("|")){
                arrUserInput[k] = arrUserInput[k] + strArr[i];
            }
            else {
                k +=1;
            }
        }
        return arrUserInput;
    }
}
