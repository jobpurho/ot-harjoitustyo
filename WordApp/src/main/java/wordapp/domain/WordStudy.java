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
            index = random.nextInt(lexicon.returnKeys().size());        
        }
    }
    
    public boolean isCorrect(String answer) {
        answered = true;
        String[] meanings = lexicon.returnMeanings(index);
        if (comparison.isSimilarEnough(answer, meanings)) {
            lexicon.removeWord(index);
            return true;
        }
        return false;
    }
    
    public boolean spellingMistake() {
        return comparison.spellingMistake();
    }

    public String returnCurrentWordAsString() {
        if (lexicon.isEmpty()) {
            return null;
        }
        return lexicon.getWord(index);
    }
    
    public String returnCurrentMeaningsAsString() {
        String meanings = String.join(", ", lexicon.returnMeanings(index));
        if (meanings.length() > 100) {
            meanings = meanings.substring(0, 100);
        }
        return meanings;
    }
    
    public void quitWordStudy() {
        if (lexicon.isEmpty()) {
            ld.removeFile();
        } else {
            ld.setFileContent(lexicon.returnContent());
            ld.save();            
        }
    }
    
    public boolean answered() {
        return answered;
    }
    
    public int returnIndex() {
        return index;
    }
}