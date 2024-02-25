package com.mycompany.caesercipher;

/**
 *
 * @author Matt
 */
public class CaeserCipher {

    public static void main(String[] args) {
        testCaeser();
        testTwoKeys();
    }
    
    public static String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public static void testCaeser() {
        int key = 15;
        String message = "CAN YOU Imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
    
    public static String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i += 2){
            char currChar = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        for (int i = 1; i < encrypted.length(); i += 2){
            char currChar = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet2.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public static void testTwoKeys(){
        int key1 = 7;
        int key2 = 19;
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println(encrypted);
        String decrypted = encryptTwoKeys(message, 26-key1, 26 - key2);
        System.out.println(decrypted);
    }
}
