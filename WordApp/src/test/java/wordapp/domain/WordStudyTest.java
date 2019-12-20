package wordapp.domain;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import wordapp.dao.*;

public class WordStudyTest {
    
    WordStudy study1;
    WordStudy study2;
    WordStudy study3;
    
    public WordStudyTest() {
        LexiconDao lexDao = new FileLexiconDao("savedTest.ser");
        
        HashMap content1 = new HashMap<>();
        content1.put("the greek word", new String[]{"meaning 1", "meaning 2", "meaning 3"});
        lexDao.setFileContent(content1);
        study1 = new WordStudy(lexDao);

        HashMap content2 = new HashMap<>();
        content2.put("the greek word", new String[]{"computer", "compute", "compete"});
        lexDao.setFileContent(content2);        
        study2 = new WordStudy(lexDao);

        FileMounceDictionary mounce = new FileMounceDictionary("dictionary.txt");
        mounce.tryToFilterTopWords(100);     
        lexDao.setFileContent(mounce.getFileContent());
        study3 = new WordStudy(lexDao);
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
    public void indexIsFromTheRange() {
        for (int i=0;i<100;i++) {
            study3.chooseNextWord();
            assertTrue(study3.getIndex()<100&&study3.getIndex()>=0);
        }
    }
    
    @Test
    public void returnCurrentMeaningsAsStringReturnsMax80Characters() {
        for (int i=0;i<100;i++) {
            study3.chooseNextWord();
            assertTrue(study3.getCurrentMeanings().length()<=83);
        }       
    }
    
    @Test
    public void spellingMistakeIsFalseWhenArticle() {
        study1.chooseNextWord();
        assertTrue(study1.isCorrect("the meaning 3"));
        assertTrue(!study1.spellingMistake());
    }    
    
    @Test
    public void spellingMistakeIsFalseWhenEqualOptionExists() {
        assertTrue(study2.isCorrect("compete"));
        assertTrue(!study2.spellingMistake());
    }       

    @Test
    public void fileExistsAfterQuit() {
        study1.quitWordStudy();
        File file = new File("savedTest.ser");
        assertTrue(file.exists());
        file.delete();
    }
    
    @Test
    public void wordRemovedFromLexiconWhenCorrect() {
        study3.chooseNextWord();
        assertTrue(study3.isCorrect(study3.getCurrentMeanings().split(",")[0]));   
        assertTrue(!study3.getLexicon().getLexiconContent().containsKey(study3.getCurrentWord()));
    }
    
    @Test
    public void wordNotRemovedFromLexiconWhenNotCorrect() {
        study3.chooseNextWord();
        assertTrue(!study3.isCorrect("RANDOM1234"));     
        assertTrue(study3.getLexicon().getLexiconContent().containsKey(study3.getCurrentWord()));
    }
}
