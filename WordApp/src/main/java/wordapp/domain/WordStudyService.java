package wordapp.domain;

import java.io.File;
import java.util.HashMap;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;
import wordapp.dao.LexiconDao;

public class WordStudyService {
    
    private WordStudy wordStudy;
    private LexiconDao lexiconDao;
    private String greekWord = "First";
    
    public WordStudyService() {
        
    }
        
    public void saveAndExit() {
        wordStudy.quitWordStudy();
        System.exit(0);
    }
    
    public boolean checkAnswer(String answer) {
        if (wordStudy.isCorrect(answer)) {
            return true;
        }
        return false;
    }
    
    public void setNext() {
        this.wordStudy.chooseNextWord();   
        greekWord = this.wordStudy.getCurrentWordAsString();        
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
    
    public String getGreekWord() {
        return greekWord;
    }
    
    public WordStudy getWordStudy() {
        return wordStudy;
    }
}
