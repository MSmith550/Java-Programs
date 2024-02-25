package oopcaesercipher;

/**
 *
 * @author Matt
 */
public class CaserCipherTwoKey {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    
    public CaserCipherTwoKey(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String getEncryptedCipher(String message){
        StringBuilder encrypted = new StringBuilder(message);
        for (int i = 0; i < encrypted.length(); i += 2){
            char currChar = Character.toUpperCase(encrypted.charAt(i));
            int idx = alphabet.indexOf(currChar);
            if (idx != -1){
                char newChar = shiftedAlphabet1.charAt(idx);
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
    
    public String decryptedMessage(String message){
        return getEncryptedCipher(message);
    }
}
