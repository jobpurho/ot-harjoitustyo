package wordapp.domain;

import wordapp.dao.*;
import java.util.*;

/**
 * This class contains domain logic of WordStudy.
 * It uses Lexicon and StringComparison objects.
 * 
 */

public class WordStudy {
    
    private Random random;
    private int index;
    private Lexicon lexicon;
    private StringComparison comparison;
    private LexiconDao ld;
    private boolean answered = false;
    private boolean spellingMistake = false;
    private String currentWord;
    private String currentMeanings;
    
    public WordStudy(LexiconDao ld) {
        this.ld = ld;
        lexicon = new Lexicon(ld);
        random = new Random();
        comparison = new StringComparison();
    }
    
    public void chooseNextWord() {
        answered = false;
        spellingMistake = true;
        if (!lexicon.isEmpty()) {
            index = random.nextInt(lexicon.getKeys().size());        
        }
        updateCurrentWord();
        updateCurrentMeanings();
        
    }
    
    public boolean isCorrect(String answer) {
        answered = true;
        boolean correct = false;
        String[] meanings = lexicon.getMeanings(index);
        for (String meaning: meanings) {
            if (comparison.isSimilarEnough(answer, meaning)) {
                correct = true;
                if (!comparison.different()) {
                    spellingMistake = false;
                }
            }            
        }
        if (correct) {
            lexicon.removeWord(index);
        }
        return correct;
    }
    
    public boolean spellingMistake() {
        return spellingMistake;
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

    public void updateCurrentWord() {
        if (lexicon.isEmpty()) {
            return;
        }
        currentWord = lexicon.getWord(index);
    } 
    
    public String getCurrentWord() {
        return currentWord;
    }    

    public void updateCurrentMeanings() {
        currentMeanings = String.join(", ", lexicon.getMeanings(index));
        if (currentMeanings.length() > 80) {
            currentMeanings = currentMeanings.substring(0, 80);
        }
        if (currentMeanings.length() == 80) {
            currentMeanings += "...";
        }
    }
    
    public String getCurrentMeanings() {
        return currentMeanings;
    }
    
    public Lexicon getLexicon() {
        return lexicon;
    }
}