package com.swingy;

public class Message {

    public static void print(String s) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
            try {
                Thread.sleep(20);
            } catch (Exception ignored) {

            }
        }
    }
}
