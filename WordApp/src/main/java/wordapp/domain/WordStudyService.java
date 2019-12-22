package wordapp.domain;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;
import wordapp.dao.LexiconDao;

/**
 * Responsibility of the class is to create an instance of WordStudy and to manage the necessary actions before quit.
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

    /**
     * Method quits instance of the WordStudy and closes the app.
     */    
    public void saveAndExit() {
        wordStudy.quitWordStudy();
        System.exit(0);
    }
    
    /**
     * Method checks if saved file exists.
     * @return boolean
     */
    public boolean savedExists() {
        if (new File(savedFileName).exists()) {
            return true;
        }
        return false;
    }

    /**
     * Method loads the file and set the content to new instance of WordStudy.
     */    
    public void load() {
        this.lexiconDao = new FileLexiconDao(savedFileName);
        this.wordStudy = new WordStudy(this.lexiconDao);
    }

    /**
     * Method tries to create new WordStudy from the given range.
     * @param input Range of words as a string.
     */     
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
    
    /**
     * Method checks if current WordStudy has started.
     * @return boolean
     */
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
