package wordapp.domain;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class StringComparison {
    
    private boolean different;
    
    private LevenshteinDistance lDistance;
    
    public StringComparison() {
        lDistance = new LevenshteinDistance();
    }

    /**
     * Method compare the answer with correct options.
     * If strings without article match, or edit distance is small, return value is true.
     * If edit distance is used, spellingMistake is set true.
     * 
     * @param answerCopy
     * @param correctStrings
     */      
    public boolean isSimilarEnough(String answerString, String correctString) {
        String answerCopy = answerString.toLowerCase();
        String correctCopy = correctString.toLowerCase();
        different = false;        
        String correctNoArticle = removeArticle(correctCopy);
        String answerNoArticle = removeArticle(answerCopy);
        String answerNoFirst = removeFirst(answerCopy);
        if (correctNoArticle.equals(answerNoArticle)) {
            return true;
        }
        different = true;        
        if (editDistanceIsSmall(answerCopy, correctCopy)) {
            return true;
        } else if (editDistanceIsSmall(correctNoArticle, answerCopy)) {
            return true;
        } else if (editDistanceIsSmall(correctNoArticle, answerNoFirst)) {
            return true;
        }  
        return false;
    }  

     /**
     * Method removes article or preposition "to" from the beginning of the word
     * 
     * @param word input string
     */     
    String removeArticle(String word) {
        if (word.split(" ").length > 0) {
            if (word.split(" ")[0].matches("(an|a|the|to)")) {
                word = word.substring(word.indexOf(" ") + 1, word.length());
            }
        }
        return word;
    }

     /**
     * Method removes any first token of a string
     * 
     * @param word input string
     */     
    String removeFirst(String word) {
        if (word.split(" ").length > 0) {
            word = word.substring(word.indexOf(" ") + 1, word.length());
        }
        return word;
    }

    
    public boolean different() {
        return different;
    }
    
    /**
    * For comparison it uses method similarPreSkipped and a class org.apache.commons.text.similarity.LevenshteinDistance
    * 
    * @param string1
    * @param string2
    */
    public boolean editDistanceIsSmall(String string1, String string2) {
        int distance = lDistance.apply(string1, string2);

        if (string1.length() > 4 && distance < 3) {
            return true;
        }
        
        return false;
    }
}