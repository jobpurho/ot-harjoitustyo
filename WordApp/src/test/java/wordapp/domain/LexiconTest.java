package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wordapp.dao.FileLexiconDao;

public class LexiconTest {
    
    Lexicon lexicon;
    
    public LexiconTest() {

        FileLexiconDao ld = new FileLexiconDao("test.ser", "file.txt");
        lexicon = new Lexicon(ld);
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
        assertEquals(lexicon.returnContent().size(), lexicon.returnKeys().size());
    }

}
