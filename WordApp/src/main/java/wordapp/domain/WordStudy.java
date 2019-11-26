package wordapp.domain;

import wordapp.dao.*;
import java.util.*;

public class WordStudy {
    
    private Random random;
    private int index;
    private Lexicon lexicon;
    private StringComparison comparison;
    private LexiconDao ld;
    
    public WordStudy(LexiconDao ld) {
        this.ld = ld;
        lexicon = new Lexicon(ld);
        random = new Random();
        comparison = new StringComparison();
    }
    
    public void chooseNextWord() {
        if (!lexicon.isEmpty()) {
            index = random.nextInt(lexicon.returnKeys().size());        
        }
    }
    
    public boolean isCorrect(String answer) {
        String[] meanings = lexicon.returnMeanings(index);
        if (comparison.isSimilar(answer, meanings)) {
            lexicon.removeWord(index);
            return true;
        }
        return false;
    }

    public String returnCurrentWordAsString() {
        if (lexicon.isEmpty()) {
            return null;
        }
        return lexicon.getWord(index);
    }
    
    public String returnCurrentMeaningsAsString() {
        return String.join(", ", lexicon.returnMeanings(index));
    }
    
    public void quitWordStudy() {
        if (lexicon.isEmpty()) {
            ld.removeFile();
        } else {
            ld.setFileContent(lexicon.returnContent());
            ld.save();            
        }
    }
    
    public int returnIndex() {
        return index;
    }
}