package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class WordStudyTest {
    
    WordStudy study;
    
    public WordStudyTest() {
        HashMap<String, String[]> words = new HashMap<>();
        words.put("word1", new String[]{"translation1"});
        words.put("word2", new String[]{"translation2"});
        study = new WordStudy(words);
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void correctAnswerRemovesWord() {
        String word = study.giveNextWord();
        study.isCorrect(study.returnCurrentMeanings()[0]);
        assertTrue(!study.returnKeys().contains(word));
        assertTrue(!study.returnDictionary().containsKey(word));
    }
}
