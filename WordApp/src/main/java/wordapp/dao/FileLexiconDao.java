package wordapp.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.*;
import java.io.*;
import wordapp.dao.*;

/**
 * 
 * This class reads saved study from file
 */

public class FileLexiconDao implements LexiconDao {

    private String file;
    private HashMap fileContent;
    private String savedFileName;
    
    /**
     * 
     * Constructors calls readSaved() method and saves the content to the HashMap
     * @param savedFileName file name
     */
    public FileLexiconDao(String savedFileName) {
        this.savedFileName = savedFileName;
        if (new File(savedFileName).exists()) {
            fileContent = readSaved();
        }        
    }
    
    /**
     * Method reads files and returns content
     * @return
     */
    private HashMap readSaved() {
        try {
            FileInputStream inputFile = new FileInputStream(savedFileName);
            ObjectInputStream input = new ObjectInputStream(inputFile);  
            return (HashMap) input.readObject();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * Methods saves content to file
     */
    public void save() {
        try {
            FileOutputStream outputFile = new FileOutputStream(savedFileName);
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            
            output.writeObject(fileContent);
            
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    /**
     * Methods removes a file
     */    
    public void removeFile() {
        File file = new File(savedFileName);
        file.delete();
    }
    
    public HashMap returnFileContent() {
        return fileContent;
    }
    
    public void setFileContent(HashMap newFileContent) {
        fileContent = newFileContent;
    }
}
