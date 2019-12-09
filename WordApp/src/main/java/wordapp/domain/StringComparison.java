package wordapp.domain;

public class StringComparison {
    
    //Jatkossa luokka hyödyntää merkkijonovertailualgoritmia tms., jotta lähes oikea vastaus voidaan hyväksyä oikeana tai osittain oikeana.
    
    public StringComparison() {
        
    }
    
    public boolean isSimilarEnough(String answerString, String[] correctStrings) {
        for (String correctString:correctStrings) {
            if (similarity(answerString, correctString) > 80) {
                return true;
            }
        }
        return false;
    }    
    
    double similarity(String answerString, String correctString) {
        if (answerString.equals(correctString)) {
            return 100;
        }
        if (skipPreEquals(answerString, correctString)) {
            return 100;
        }
        return levensthein();
    }
    
    //skip an article or preposition in correct string
    boolean skipPreEquals(String answerString, String correctString) {
        if (correctString.split(" ").length == 2 && correctString.split(" ")[0].length() <= 3 && correctString.split(" ")[1].length() >= 3) {
            if (correctString.split(" ")[1].equals(answerString)) {
                return true;
            }
        }
        return false;
    }
    
    double levensthein() {
        //merkkijonovertailu
        return 0;
    }
}