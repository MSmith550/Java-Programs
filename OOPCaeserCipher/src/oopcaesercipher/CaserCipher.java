package oopcaesercipher;

/**
 *
 * @author Matt
 */
public class CaserCipher {
    private String alphabet;
    private String shiftedAlphabet;
    
    public CaserCipher(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
    }
    
    public String getEncryptedCipher(String message){
        StringBuilder encrypted = new StringBuilder(message);
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
    
    public String decryptedMessage(String message){
        return getEncryptedCipher(message);
    }
}
