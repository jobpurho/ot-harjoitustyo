package wordapp.domain;

import java.util.*;

public class WordStudy {
    
    private ArrayList<String> keys;
    private HashMap<String, String[]> dictionary;
    private Random random;
    private int index;
    private String currentMeanings;
    
    public WordStudy(HashMap dictionary) {
        random = new Random();
        this.dictionary = dictionary;
        this.keys = new ArrayList<String>(dictionary.keySet());
        currentMeanings = null;
    }
    
    public String giveNextWord() {
        if (this.keys.size()==0) {
            return null;
        }
        index = random.nextInt(this.keys.size());
        String word = this.keys.get(index);
        return word;
    }
    
    public boolean isCorrect(String answer) {
        String word = keys.get(index);
        currentMeanings = String.join(", ", dictionary.get(keys.get(index)));
        for (String translation:this.dictionary.get(word)) {
            if (answer.equals(translation)) {
                removeWord();
                return true;
            }
        }
        return false;
    }
    
    private void removeWord() {
        dictionary.remove(keys.get(index));
        keys.remove(index);
    }

    public HashMap returnDictionary() {
        return this.dictionary;
    }
    
    public ArrayList returnKeys() {
        return this.keys;
    }
    
    public String returnCurrentKey() {
        return keys.get(index);
    }

    public String[] returnCurrentMeanings() {
        return dictionary.get(keys.get(index));
    }
    
        
    public String returnCurrentMeaningsAsString() {
        return currentMeanings;
    }
    
}
