package oopcaesercipher;

/**
 *
 * @author Matt
 */
public class OOPCaeserCipher {

    public static void main(String[] args) {
        CaserCipher cc = new CaserCipher(15);
        CaserCipher cc2 = new CaserCipher(26 - 15);
        System.out.println("Encrypted message: " + cc.getEncryptedCipher("FREE CAKE IN THE CONFERENCE ROOM!"));
        System.out.println("Decrypted message: " + cc2.decryptedMessage("UGTT RPZT XC IWT RDCUTGTCRT GDDB!"));
        
        CaserCipherTwoKey cc2k = new CaserCipherTwoKey(15, 21);
        CaserCipherTwoKey cc2k2 = new CaserCipherTwoKey(26 - 15, 26 - 21);
        System.out.println("Encrypted message: " + cc2k.getEncryptedCipher("FREE CAKE IN THE CONFERENCE ROOM!"));
        System.out.println("Decrypted message: " + cc2k2.decryptedMessage("UMTZ XPFT XI OWZ XDIUZGZCXT GJDH!"));
        
    }
    
}
