package wordapp.domain;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;
import wordapp.dao.LexiconDao;

/**
 * This class contains methods for user interface
 *
 */

public class WordStudyService {
    
    private WordStudy wordStudy;
    private LexiconDao lexiconDao;
    private boolean started;
    private String savedFileName;
    
    public WordStudyService() {
        started = false;
        savedFileName = "saved.ser";
    }
        
    public void saveAndExit() {
        wordStudy.quitWordStudy();
        System.exit(0);
    }
    
    public boolean savedExists() {
        if (new File(savedFileName).exists()) {
            return true;
        }
        return false;
    }
    
    public void load() {
        this.lexiconDao = new FileLexiconDao(savedFileName);
        this.wordStudy = new WordStudy(this.lexiconDao);
    }
    
    public boolean tryToCreateNew(String input) {
        try {
            int number = Integer.parseInt(input);
            this.lexiconDao = new FileLexiconDao(savedFileName);
            FileMounceDictionary mounce = new FileMounceDictionary("dictionary.txt");
            if (!mounce.tryToFilterTopWords(number)) {
                return false;
            }
            HashMap fileContent = mounce.getFileContent();        
            this.lexiconDao.setFileContent(fileContent);    
            this.wordStudy = new WordStudy(this.lexiconDao);
            return true;
        } catch (Exception e) {     
            return false;
        }    
    }
    
    public WordStudy getWordStudy() {
        return wordStudy;
    }
    
    public boolean started() {
        if (!started) {
            if (wordStudy != null) {
                started = true;
            }            
            return false;
        } else {
            return true;
        }
    }
    
    public void setSavedFile(String fileName) {
        savedFileName = fileName;
    }
}
