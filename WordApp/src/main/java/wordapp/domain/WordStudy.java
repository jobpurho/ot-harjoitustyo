package wordapp.domain;

import wordapp.dao.*;
import java.util.*;

public class WordStudy {
    
    private Random random;
    private int index;
    private Lexicon lexicon;
    private StringComparison comparison;
    private LexiconDao ld;
    private boolean answered = false;
    
    public WordStudy(LexiconDao ld) {
        this.ld = ld;
        lexicon = new Lexicon(ld);
        random = new Random();
        comparison = new StringComparison();
    }
    
    public void chooseNextWord() {
        answered = false;
        if (!lexicon.isEmpty()) {
            index = random.nextInt(lexicon.getKeys().size());        
        }
    }
    
    public boolean isCorrect(String answer) {
        answered = true;
        String[] meanings = lexicon.getMeanings(index);
        if (comparison.isSimilarEnough(answer, meanings)) {
            lexicon.removeWord(index);
            return true;
        }
        return false;
    }
    
    public boolean spellingMistake() {
        return comparison.spellingMistake();
    }

    public void quitWordStudy() {
        if (lexicon.isEmpty()) {
            ld.removeFile();
        } else {
            ld.setFileContent(lexicon.getLexiconContent());
            ld.save();            
        }
    }
    
    public boolean answered() {
        return answered;
    }
    
    public int getIndex() {
        return index;
    }
    
    public String getCurrentWordAsString() {
        if (lexicon.isEmpty()) {
            return null;
        }
        return lexicon.getWord(index);
    }    
    
    public String getCurrentMeaningsAsString() {
        String meanings = String.join(", ", lexicon.getMeanings(index));
        if (meanings.length() > 100) {
            meanings = meanings.substring(0, 100);
        }
        return meanings;
    }
}