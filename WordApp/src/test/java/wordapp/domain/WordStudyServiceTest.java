package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordapp.dao.FileLexiconDao;
import wordapp.dao.FileMounceDictionary;
import wordapp.dao.LexiconDao;

public class WordStudyServiceTest {
    
    private WordStudy study;
    private WordStudyService service;
    
    public WordStudyServiceTest() {
        service = new WordStudyService();
        LexiconDao lexDao = new FileLexiconDao("saved.ser");
        FileMounceDictionary mounce = new FileMounceDictionary("dictionary.txt");
        mounce.tryToFilterTopWords(100);     
        lexDao.setFileContent(mounce.getFileContent());
        study = new WordStudy(lexDao);
        
        if (!service.savedExists()) {
            lexDao.save();
        }
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
    public void cannotBeStartedIfWordStudyIsNull() {
        assertTrue(!service.started());
        assertTrue(!service.started());
        assertTrue(!service.started());
    }

    @Test
    public void wordStudyIsNotNullAfterLoading() {        
        service.load();
        assertNotNull(service.getWordStudy());
    }
    
    @Test
    public void tryToCreateNewReturnsTrueWithCorrectInput() {
        assertTrue(service.tryToCreateNew("1"));
        assertTrue(service.tryToCreateNew("5381"));
    }
    
    @Test
    public void tryToCreateNewReturnsFalseWithInCorrectInput() {
        assertTrue(!service.tryToCreateNew("-1"));
        assertTrue(!service.tryToCreateNew("5382"));
        assertTrue(!service.tryToCreateNew(null));
        assertTrue(!service.tryToCreateNew("abcd"));        
    }
}
