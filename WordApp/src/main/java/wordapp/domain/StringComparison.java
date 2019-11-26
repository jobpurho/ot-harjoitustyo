package wordapp.domain;

public class StringComparison {
    
    //Jatkossa luokka hyödyntää merkkijonovertailualgoritmia tms., jotta lähes oikea vastaus voidaan hyväksyä oikeana tai osittain oikeana.
    
    public StringComparison() {
        
    }
    
    public boolean isSimilar(String word, String[] translations) {
        for (String translation:translations) {
            if (word.equals(translation)) {
                return true;
            }
        }
        return false;
    }    
}