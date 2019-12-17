package wordapp.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class FileMounceDictionaryTest {
    
    private FileMounceDictionary mounce;
    
    public FileMounceDictionaryTest() {
        mounce = new FileMounceDictionary("dictionary.txt");
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
    public void fileContentIsNotEmpty() {
        assertTrue(mounce.getFileContent().size()>0);
    }
    
    @Test
    public void fileContentSizeIsCorrectAfterFiltering() {
        mounce.tryToFilterTopWords(10);
        assertTrue(mounce.getFileContent().size()==10);
        mounce.tryToFilterTopWords(100);
        assertTrue(mounce.getFileContent().size()==100);
        mounce.tryToFilterTopWords(200);
        assertTrue(mounce.getFileContent().size()==200);
        mounce.tryToFilterTopWords(1000);
        assertTrue(mounce.getFileContent().size()==1000);
        mounce.tryToFilterTopWords(4000);
        assertTrue(mounce.getFileContent().size()==4000);
    }
}
