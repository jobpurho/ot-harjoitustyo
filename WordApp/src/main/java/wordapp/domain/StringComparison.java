package wordapp.domain;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class StringComparison {
    
    private boolean spellingMistake;
    private boolean verb;
    private boolean noun;
    
    private LevenshteinDistance lDistance;
    
    public StringComparison() {
        lDistance = new LevenshteinDistance();
    }

    /**
     * Method uses class org.apache.commons.text.similarity.LevenshteinDistance to count the edit distance of the two strings
     * 
     * @param answerString
     * @param correctStrings
     */      
    public boolean isSimilarEnough(String answerString, String[] correctStrings) {
        noun = false;
        verb = false;
        spellingMistake = false;
        for (String correctString:correctStrings) {
            if (correctString.split(" ").length > 1) {
                if (correctString.split(" ")[0].matches("(an|a|the)")) {
                    noun = true;
                } else if (correctString.split(" ")[0].equals("to")) {
                    verb = true;
                }
            }
            if (similarPreSkipped(answerString, correctString)) {
                return true;
            } 
            if (answerString.length() > 4 && lDistance.apply(answerString, correctString) < 3) {
                spellingMistake = true;
                return true;
            }
        }
        return false;
    }    
    
    boolean similarPreSkipped(String answerString, String correctString) {
        String answerStringLower = answerString.toLowerCase();
        String correctStringLower = correctString.toLowerCase();
        if (answerStringLower.equals(correctStringLower)) {
            return true;
        }
        if (skipPreEquals(answerStringLower, correctStringLower) || skipPreEquals(correctStringLower, answerStringLower)) {
            checkSpelling(answerStringLower, correctStringLower);
            return true;
        }
        return false;
    }
    
    //skip an article or preposition in correct string
    boolean skipPreEquals(String firstString, String secondString) {
        String[] firstWords = firstString.split(" ");
        String[] secondWords = secondString.split(" ");
        int a = secondString.indexOf(" ") + 1;
        int b = secondString.length();
        int c = firstString.indexOf(" ") + 1;
        int d = firstString.length();
        if (secondWords.length > 1 && firstWords.length > 1) {
            if (secondString.substring(a, b).equals(firstString.substring(c, d))) {         
                return true;
            }            
        }
        if (secondWords.length > 1 && secondWords[0].length() <= 3 && secondWords[1].length() >= 2) {
            if (secondString.substring(a, b).equals(firstString)) { 
                return true;
            }
        }
        return false;
    }
    
    void checkSpelling(String answer, String correct) {
        if (answer.split(" ").length < 2) {
            spellingMistake = false;
        } else if (verb && !noun && !answer.split(" ")[0].equals("to")) {
            spellingMistake = true;
        } else if (!verb && noun && answer.split(" ")[0].length() <= 3) {
            if (!answer.split(" ")[0].matches("(the|an|a)")) {
                spellingMistake = true;
            } else if (answer.split(" ")[0].equals("an") && !answer.split(" ")[1].substring(0, 1).matches("[aeiouAEIOU]")) {
                spellingMistake = true;
            } else if (answer.split(" ")[0].equals("a") && answer.split(" ")[1].substring(0, 1).matches("[aeiouAEIOU]")) {
                spellingMistake = true;
            }
        }
    }
    
    public boolean spellingMistake() {
        return spellingMistake;
    }
}