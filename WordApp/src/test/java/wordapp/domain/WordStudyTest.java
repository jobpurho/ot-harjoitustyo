package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import wordapp.dao.*;

public class WordStudyTest {
    
    WordStudy study;
    
    public WordStudyTest() {
        LexiconDao lexDao = new FileLexiconDao("saved.ser");
        OriginalLexicon lexicon = new FileOriginalLexicon("file.txt");
        lexDao.setFileContent(lexicon.returnFileContent());
        study = new WordStudy(lexDao);
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
            study.chooseNextWord();
            assertTrue(study.returnIndex()<4&&study.returnIndex()>=0);
        }
    }

}
