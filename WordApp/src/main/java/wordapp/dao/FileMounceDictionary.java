package wordapp.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

//Tämä on luonnos sanakirjan lukemista varten, joka ei vielä toimi täydellisesti. Parempi versio tulee myöhemmin.

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
        try {
            Scanner fileReader = new Scanner(new File(mounceFile)); 
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.length() < 5) {
                    continue;
                } else {
                    filter(line);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void filter(String line) {
        
        if (line.matches(".*[0-9]x .*")) {
            String[] parts = line.split("x ");
        } else if (line.substring(0 , 3).equals("GK ")) {
            String[] parts = line.split(" ");
            number = Integer.parseInt(parts[parts.length - 1].replaceAll("[x,]" , ""));
            greekWord = parts[3];
        } else if (line.substring(0, 5).equals("<def>")) {
            String[] translations = editDefinition(line);
            if (!lines.containsKey(number)) {
                lines.put(number , new ArrayList<>());
            }
            lines.get(number).add(greekWord);
            this.translations.put(greekWord, translations);
        }
    }
    
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
    
    String splitOr(String part) {
        if (part.contains("or")) {
            String a = new String(part).replaceAll(" [a-zA-Z]+ or ", " ");
            String b = new String(part).replaceAll(" or [a-zA-Z]+ ", " ");
            part = a + "," + b;
        }
        return part;
    }
    
    boolean verseNumber(String part) {
        if (part.matches("(([0-9] ){0,1}[a-zA-Z]{2,5}\\.{0,1} ){0,1}[0-9]{1,2}(:[0-9]{1,2}){0,1}.{0,10}")) {
            return true;
        }
        if (part.matches("[0-9]{1,2}")) {
            return true;
        }
        return false;
    }
    
    String clean(String part) {
        if (part.matches(".*[α-ω\\.].*")) {
            return "";
        }
        if (part.split(" ").length>7) {
            return "";
        }
        if (part.matches(".*(NT|perfect|from the Hebrew).*")) {
            return "";
        }
        part = part.replaceAll("\\([a-zA-Z0-9]\\) *", "");
        if (part.substring(0,1).equals(" ")) {
            part = part.substring(1, part.length());
        }
        return part;
    }
    
    public void filterTopWords(int number) {
        TreeMap<Integer, ArrayList<String>> linesCopy = new TreeMap<>(lines);
        fileContent = new HashMap<>();
        int i = 0;
        while (true) {
            ArrayList<String> greekWords  = linesCopy.get(linesCopy.lastKey());
            for (String word:greekWords) {
                fileContent.put(word, translations.get(word));
                i++;
                if (i == number) {
                    break;
                }            
            }
            if (i == number) {
                break;
            }           
            linesCopy.pollLastEntry();
        }
        filtered = true;    
    }
    
    public HashMap returnFileContent() {
        if (!filtered) {
            filterTopWords(100);
        }
        return fileContent;
    }
}