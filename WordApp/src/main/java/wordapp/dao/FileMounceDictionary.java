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
        fileContent = new HashMap<>();
        try {
            Scanner fileReader = new Scanner(new File(mounceFile)); 
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.length()<5) {
                    continue;
                }
                else {
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
        }
        else if (line.substring(0,3).equals("GK ")) {
            String[] parts = line.split(" ");
            number = Integer.parseInt(parts[parts.length-1].replaceAll("[x,]", ""));
            greekWord = parts[3];
        }
                
        else if (line.substring(0,5).equals("<def>")) {
            String[] translations = editTranslations(line);
            if (!lines.containsKey(number)) {
                lines.put(number, new ArrayList<>());
            }
            lines.get(number).add(greekWord);
            this.translations.put(greekWord, translations);
        }
    }
    
    public String[] editTranslations(String translation) {
        translation = translation.replaceAll("(<def>|</def>)", "");
        translation = translation.replaceAll(" [A-Za-z]*\\. ", "");
        translation = translation.replaceAll("Acts", "");              
        translation = translation.replaceAll("[0-9]*:[0-9]*[;]*", "");
        translation = translation.replaceAll("[0-9\\*→]", "");       
        translation = translation.replace("()", ""); 
        translation = translation.replaceAll(";;* *", ","); 
        translation = translation.replaceAll("(,,* *)(,,* *)*", ","); 
        translation = translation.replace("(x)", "");
        translation = translation.substring(0,translation.length()-1);
        return translation.split(",");
    }
    
    public void filterTopWords(int number) {
        for (int i=0;i<number;i++) {
            ArrayList<String> greekWords  = lines.get(lines.lastKey());
            for (String j:greekWords) {
                fileContent.put(j, translations.get(j));
                i++;
            }
            lines.pollLastEntry();
        }
        filtered = true;    
    }
    
    public HashMap returnFileContent() {
        if (!filtered) {
            filterTopWords(10);
        }
        return fileContent;
    }
}
