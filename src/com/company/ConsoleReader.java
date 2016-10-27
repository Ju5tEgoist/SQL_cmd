package com.company;

import java.util.Scanner;

/**
 * Created by yulia on 13.10.16.
 */
public class ConsoleReader implements ConsoleManager {
    @Override
    public String read() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }
}
