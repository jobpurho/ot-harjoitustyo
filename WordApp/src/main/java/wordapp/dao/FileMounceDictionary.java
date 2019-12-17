package wordapp.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class FileMounceDictionary implements OriginalLexicon {

    private TreeMap<Integer, ArrayList<String>> lines;
    private HashMap<String, String[]> translations;
    private HashMap<String, String[]> fileContent;
    private int number;
    private String greekWord;
    private boolean filtered;

    public FileMounceDictionary(String mounceFile) {
        lines = new TreeMap<>();
        translations = new HashMap<>();
        int i=0;
        try {
            Scanner fileReader = new Scanner(new File(mounceFile)); 
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.length() < 5) {
                    continue;
                } else {
                    filter(line);
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    /**
     * Method creates a list of greek words with frequency and a list of meanings
     * 
     * @param line line of the file
     */
    public void filter(String line) {
        
        if (line.matches(".*[0-9]x .*")) {
            String[] parts = line.split("x ");
        } else if (line.substring(0 , 3).equals("GK ")) {
            String[] parts = line.split(" ");
            number = Integer.parseInt(parts[parts.length - 1].replaceAll("[x,]" , ""));
            greekWord = parts[3];
            greekWord = createDuplicate(greekWord);
        } else if (line.substring(0, 5).equals("<def>")) {
            String[] translations = editDefinition(line);
            if (!lines.containsKey(number)) {
                lines.put(number , new ArrayList<>());
            }            
            lines.get(number).add(greekWord);            
            this.translations.put(greekWord, translations);
        }
    }
    
    /**
     * Method split definition in dictionary to parts by string "; "
     * Method returns a list of words as a string separated by ","
     * @param translation definition for the greek word
     */
    public String[] editDefinition(String translation) {
        String output = "";
        String[] translationArray = translation.replaceAll("(<def>|</def>.*)", "").split("; ");
        for (String part:translationArray) {
            String outputPart = editPartOfDef(part);
            if (!outputPart.equals("")) {
                if (output.equals("")) {
                    output = editPartOfDef(part);
                } else {
                    output = output  + "," + editPartOfDef(part);
                }                
            }           
        }
        return output.split(",");
    }
    
    /**
     * Method split part of the definition to subparts (words) by string ", "
     * Method returns a list of words as a string separated by ","
     * @param part part of the definition for the greek word
     */
    String editPartOfDef(String part) {
        String output = "";
        part = part.replace("!", "!,");
        part = part.replace("?", "?,");
        part = splitOr(part);
        for (String subPart:part.split(", ")) {
            if (!verseNumber(subPart)) {
                subPart = clean(subPart);
                if (!subPart.equals("")) {                    
                    if (output.equals("")) {
                        output = clean(subPart);
                    } else {
                        output = output + "," + clean(subPart);
                    }                    
                }
            }
        }        
        return output;
    }
    
    /**
     * Method creates to separate items from the part of definition if it contains "or"
     * 
     * @param part part of the definition for the greek word
     */
    String splitOr(String part) {
        if (part.contains("or")) {
            String a = new String(part).replaceAll(" [a-zA-Z]+ or ", " ");
            String b = new String(part).replaceAll(" or [a-zA-Z]+ ", " ");
            part = a + "," + b;
        }
        return part;
    }
    
    /**
     * Method tells if the string is verse number
     * 
     * @param string input string
     */
    boolean verseNumber(String string) {
        if (string.matches("(([0-9] ){0,1}[a-zA-Z]{2,5}\\.{0,1} ){0,1}[0-9]{1,2}(:[0-9]{1,2}){0,1}.{0,10}")) {
            return true;
        }
        if (string.matches("[0-9]{1,2}")) {
            return true;
        }
        return false;
    }

    /**
     * Method remove unnecessary information from the definition
     * 
     * @param part part of the definition for the greek word
     */    
    String clean(String part) {
        if (part.matches(".*[α-ω\\.].*")) {
            return "";
        }
        if (part.split(" ").length > 7) {
            return "";
        }
        if (part.matches(".*(NT|perfect|from the Hebrew).*")) {
            return "";
        }
        part = part.replaceAll("\\([a-zA-Z0-9]\\) *", "");
        if (part.substring(0, 1).equals(" ")) {
            part = part.substring(1, part.length());
        }
        return part;
    }

    /**
     * Method returns true if filtering can be done by number
     * Method calls filtering method
     * 
     * @param number number of top words
     */     
    public boolean tryToFilter(int number) {
        if (number<1||number>translations.size()) {
            return false;
        }
        filterTopWords(number);
        filtered = true;   
        return true;
    }
    
     /**
     * Method filter the top words limited by number
     * 
     * @param number number of top words
     */ 
    public void filterTopWords(int number) {
        TreeMap<Integer, ArrayList<String>> linesCopy = new TreeMap<>(lines);
        fileContent = new HashMap<>();
        while (fileContent.size()!=number) {
            ArrayList<String> greekWords  = linesCopy.get(linesCopy.lastKey());
            for (String word:greekWords) {
                fileContent.put(word, translations.get(word));
                if (fileContent.size()==number) {
                    break;
                }            
            }          
            linesCopy.pollLastEntry();
        }        
    }
    
    String createDuplicate(String word) {
        while (translations.containsKey(word)) {
            if (!word.contains("I")) {
                word+= " I";
            }
            word += "I";
        }        
        return word;
    }
    
    public HashMap getFileContent() {
        if (!filtered) {
            tryToFilter(100);
        }
        return fileContent;
    }
    
    public int getWordCount() {
        return lines.size();
    }
}