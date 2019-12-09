package wordapp.domain;

public class StringComparison {
    
    //Jatkossa luokka hyödyntää merkkijonovertailualgoritmia tms., jotta lähes oikea vastaus voidaan hyväksyä oikeana tai osittain oikeana.
    private boolean spellingMistake;
    private boolean verb;
    private boolean noun;
    
    public StringComparison() {
        
    }
    
    public boolean isSimilarEnough(String answerString, String[] correctStrings) {
        noun = false;
        verb = false;
        spellingMistake = false;
        for (String correctString:correctStrings) {
            if (correctString.split(" ").length>1) {
                if (correctString.split(" ")[0].matches("(an|a|the)")) {
                    noun = true;
                } else if (correctString.split(" ")[0].equals("to")){
                    verb = true;
                }
            }
            if (similarity(answerString, correctString) > 80) {
                return true;
            }
        }
        return false;
    }    
    
    double similarity(String answerString, String correctString) {
        String answerStringLower = answerString.toLowerCase();
        String correctStringLower = correctString.toLowerCase();
        if (answerStringLower.equals(correctStringLower)) {
            return 100;
        }
        if (skipPreEquals(answerStringLower, correctStringLower) || skipPreEquals(correctStringLower, answerStringLower)) {
            checkSpelling(answerStringLower, correctStringLower);
            return 100;
        }
        return levensthein();
    }
    
    //skip an article or preposition in correct string
    boolean skipPreEquals(String firstString, String secondString) {
        String[] firstWords = firstString.split(" ");
        String[] secondWords = secondString.split(" ");
        int a = secondString.indexOf(" ")+1;
        int b = secondString.length();
        int c = firstString.indexOf(" ")+1;
        int d = firstString.length();
        System.out.println("answer:" + secondString.substring(a, b));
        System.out.println("correct:" + firstString);
        if (secondWords.length > 1 && firstWords.length > 1) {
            if (secondString.substring(a, b).equals(firstString.substring(c,d))) {         
                return true;
            }            
        } if (secondWords.length > 1 && secondWords[0].length() <= 3 && secondWords[1].length() >= 2) {
            if (secondString.substring(a, b).equals(firstString)) { 
                return true;
            }
        }
        return false;
    }
    
    void checkSpelling(String answer, String correct) {
        if (answer.split(" ").length<2) {
            
        } else if (verb && !noun && !answer.split(" ")[0].equals("to")) {
            spellingMistake = true;
        } else if (!verb && noun && answer.split(" ")[0].length()<=3) {
            if (!answer.split(" ")[0].matches("(the|an|a)")) {
                spellingMistake = true;
            } else if (answer.split(" ")[0].equals("an") && !answer.split(" ")[1].substring(0,1).matches("[aeiouAEIOU]")) {
                spellingMistake = true;
            } else if (answer.split(" ")[0].equals("a") && answer.split(" ")[1].substring(0,1).matches("[aeiouAEIOU]")) {
                spellingMistake = true;
            }
        }
    }
    
    double levensthein() {
        //merkkijonovertailu
        return 0;
    }
    
    public boolean spellingMistake() {
        return spellingMistake;
    }
}