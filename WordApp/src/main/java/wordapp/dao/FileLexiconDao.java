package wordapp.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.*;
import java.io.*;
import wordapp.dao.*;

public class FileLexiconDao implements LexiconDao {

    private String file;
    private HashMap fileContent;
    private String savedFileName;
    
    public FileLexiconDao(String savedFileName, String originalFileName) {
        this.savedFileName = savedFileName;
        if (new File(savedFileName).exists()) {
            fileContent = readSaved();
        } else {
            fileContent = new OriginalLexicon(originalFileName).returnFileContent();
        }        
    }
    
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

    public void save(){
        try {
            FileOutputStream outputFile = new FileOutputStream(savedFileName);
            ObjectOutputStream output = new ObjectOutputStream(outputFile);
            
            output.writeObject(fileContent);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
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
    
    public void removeSaved() {
        
    }
}
