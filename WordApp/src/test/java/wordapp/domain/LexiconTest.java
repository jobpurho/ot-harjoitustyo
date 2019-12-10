package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordapp.dao.*;

public class LexiconTest {
    
    Lexicon lexicon;
    
    public LexiconTest() {
        LexiconDao lexDao = new FileLexiconDao("saved.ser");
        OriginalLexicon lexicon = new FileMounceDictionary("dictionary.txt");
        lexDao.setFileContent(lexicon.getFileContent());
        this.lexicon = new Lexicon(lexDao);
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
    public void sizeOfContentAndKeysIsSame() {
        assertEquals(lexicon.getLexiconContent().size(), lexicon.getKeys().size());
    }

}
