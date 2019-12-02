package wordapp.dao;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOriginalLexicon implements OriginalLexicon {
    
    private String file;
    private HashMap<String, String[]> lines;
    
    public FileOriginalLexicon(String file) {
        this.file = file;
        this.lines = new HashMap<>();
        
        try {
            Files.lines(Paths.get(file))
                    .map(line->line.split(","))
                    .forEach(line->this.lines.put(line[0], line[1].split(";")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public HashMap returnFileContent() {
        return this.lines;
    }    
    
}
