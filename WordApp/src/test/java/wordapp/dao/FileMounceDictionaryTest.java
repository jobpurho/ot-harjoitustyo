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
        mounce.filterTopWords(10);
        assertTrue(mounce.getFileContent().size()==10);
        mounce.filterTopWords(100);
        assertTrue(mounce.getFileContent().size()==100);
        mounce.filterTopWords(200);
        assertTrue(mounce.getFileContent().size()==200);
        mounce.filterTopWords(1000);
        assertTrue(mounce.getFileContent().size()==1000);
    }
}
