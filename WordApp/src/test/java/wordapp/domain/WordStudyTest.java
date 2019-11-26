package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import wordapp.dao.FileLexiconDao;

public class WordStudyTest {
    
    WordStudy study;
    
    public WordStudyTest() {
        FileLexiconDao fldao = new FileLexiconDao("saves.ser","file.txt");
        study = new WordStudy(fldao);
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
