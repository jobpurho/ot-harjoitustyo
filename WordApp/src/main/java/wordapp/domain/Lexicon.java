package wordapp.domain;

import java.util.*;
import wordapp.dao.*;

/**
 * This class is for lexicon used by WordStudy
 * 
 */

public class Lexicon {

    private ArrayList<String> keys;
    private HashMap<String, String[]> lexiconContent;
    
    /**
     * constructor
     * @param ld 
     */
    public Lexicon(LexiconDao ld) {
        lexiconContent = ld.returnFileContent();
        keys = new ArrayList<String>(lexiconContent.keySet());
    }
    
    /**
     * Method removes word by index
     * @param index 
     */
    public void removeWord(int index) {
        lexiconContent.remove(keys.get(index));
        keys.remove(index);
    } 

    /**
     * Method check if there are word
     * @param index 
     */    
    public boolean isEmpty() {
        if (this.keys.size() == 0) {
            return true;
        }
        return false;
    }
    
    public ArrayList getKeys() {
        return this.keys;
    }

    public String[] getMeanings(int index) {
        return lexiconContent.get(this.keys.get(index));
    }
    
    public String getWord(int index) {
        return this.keys.get(index);
    }
    
    public HashMap getLexiconContent() {
        return this.lexiconContent;
    }
}
