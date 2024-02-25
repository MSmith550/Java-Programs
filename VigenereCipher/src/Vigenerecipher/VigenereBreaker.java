package Vigenerecipher;

import java.io.File;
import java.util.*;
import edu.duke.*;

/**
 *
 * @author Matt
 */
public class VigenereBreaker {
    public static String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            slice.append(message.charAt(i));
        }
        
        return slice.toString();
    }

    public static int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker CC = new CaesarCracker(mostCommon);
        for(int i = 0; i < klength; i++){
            String slice = sliceString(encrypted, i, klength);
            int dkey = CC.getKey(slice);
            key[i] = dkey;
        }
        return key;
    }

    public static void breakVigenere() {
        //add to read from all dictionary files
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            HashSet<String> dictionary = readDictionary(fr);
            languages.put(f.getName(), dictionary);
        }

        FileResource fr2 = new FileResource();
        String message = fr2.asString();
        
        //call new breakForAllLangs method
        breakForAllLangs(message, languages);
        
        
        //String decryption = breakForLanguage(message, dictionary);
        //System.out.println(decryption);
        
    }
    
    public static HashSet<String> readDictionary (FileResource fr){
        HashSet<String> dic = new HashSet<>();
        for(String word : fr.words()){
            dic.add(word.toLowerCase());
        }
        
        return dic;
    }
    
    public static int countWords (String message, HashSet<String> dictionary){
        int count = 0;
        String[] words = message.split("\\W+");
        for(String word : words){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
                count++;
            }
        }
        
        return count;
    }
    
    public static String breakForLanguage (String encrypted, HashSet<String> dictionary){
        String decryption = "";
        ArrayList<Integer> bestKey = new ArrayList<>();
        int currCount = 0;
        int count = 0;
        Character mostCommonChar = mostCommonCharIn(dictionary);
        for(int i = 1; i < 101 ; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher VC = new VigenereCipher(keys);
            String answer = VC.decrypt(encrypted);
            count = countWords(answer, dictionary);
            if(count > currCount){
                bestKey.clear();
                for(int k = 0; k < keys.length; k++){
                    bestKey.add(keys[k]);
                }
                currCount = count;
            }
        }
        int[] bestPossibleKey = new int[bestKey.size()];
        for (int i = 0; i < bestKey.size(); i++){
            bestPossibleKey[i] = bestKey.get(i);
        }
        VigenereCipher VC2 = new VigenereCipher(bestPossibleKey);
        decryption = VC2.decrypt(encrypted);
        return decryption;
    }
    
    public static Character mostCommonCharIn(HashSet<String> dictionary){
        Character mostChar;
        ArrayList<Character> alphabet = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        for(String word : dictionary){
            for(int i = 0; i < word.length(); i++){
                Character currChar = word.charAt(i);
                if(!alphabet.contains(currChar)){
                    alphabet.add(currChar);
                    counts.add(1);
                }else{
                    int idx = alphabet.indexOf(currChar);
                    int count = counts.get(idx);
                    counts.set(idx, count + 1);
                }
            }
        }
        int bigCount = 0;
        for(Integer i : counts){
            if(i > bigCount){
                bigCount = i;
            }
        }
        mostChar = alphabet.get(counts.indexOf(bigCount));
        
        return mostChar;
    }
    
    public static void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        String mostCountedWords = "";
        int maxCount = 0;

        //try each language in languages.keySet()
        for(String s : languages.keySet()){
            //use breakforlanguage. use .get to get the words from the hashMap
            String message = breakForLanguage(encrypted, languages.get(s));
            //get a count for how many words match
            int counting = countWords(message, languages.get(s));
            counts.put(s, counting);
        }
        //finds the highest count
        for(Integer c : counts.values()){
            if(c > maxCount){
                maxCount = c;
            }
        }
        //pick the langage that has the highest count
        for(String s : counts.keySet()){
            if(counts.get(s) == maxCount){
                mostCountedWords = s;
            }
        }
        //print its decryption
        System.out.println(breakForLanguage(encrypted, languages.get(mostCountedWords)));
        System.out.println(mostCountedWords);
    }
    
    //Tests for the methods in this class
    public static void testMostCommonCharIn(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        Character mostCommon = mostCommonCharIn(dictionary);
        System.out.println(mostCommon);
    }
    
    public static void testBreakForLanguage(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        FileResource fr2 = new FileResource();
        String message = fr2.asString();
        String decryption = breakForLanguage(message, dictionary);
        System.out.println(decryption);
    }
    
    public static void testCountWords(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        FileResource fr2 = new FileResource();
        String message = fr2.asString();
        System.out.print(countWords(message, dictionary));
    }
    
    public static void testReadDictionary(){
        FileResource fr = new FileResource();
        System.out.print(readDictionary(fr));
    }
    
    public static void testKeyLength(){
        FileResource fr = new FileResource();
        String message = fr.asString();       
        int[] keys = tryKeyLength(message, 38, 'e');
        for(int key : keys){
            System.out.println(key);
        }
    }
    
    public static void testSliceString(){
        //test for sliceString(), should return dhl
        System.out.println(sliceString("abcdefghijklm", 3, 4));
    }
    
    public static void quiz(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        FileResource fr2 = new FileResource();
        String message = fr2.asString();
        int[] keys = tryKeyLength(message, 38, 'e');
        VigenereCipher VC2 = new VigenereCipher(keys);
        String decryption = VC2.decrypt(message);
        System.out.print(countWords(decryption, dictionary));
    }
}
