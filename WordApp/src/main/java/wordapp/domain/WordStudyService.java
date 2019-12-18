package wordapp.domain;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;
import wordapp.dao.LexiconDao;

public class WordStudyService {
    
    private WordStudy wordStudy;
    private LexiconDao lexiconDao;
    private boolean started;
    
    public WordStudyService() {
        started = false;
    }
        
    public void saveAndExit() {
        wordStudy.quitWordStudy();
        System.exit(0);
    }
    
    public boolean savedExists() {
        if (new File("saved.ser").exists()) {
            return true;
        }
        return false;
    }
    
    public void load() {
        this.lexiconDao = new FileLexiconDao("saved.ser");
        this.wordStudy = new WordStudy(this.lexiconDao);
    }
    
    public boolean tryToCreateNew(String input) {
        try {
            int number = Integer.parseInt(input);
            this.lexiconDao = new FileLexiconDao("saved.ser");
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
            started = true;
            return false;
        } else {
            return true;
        }
    }
}
