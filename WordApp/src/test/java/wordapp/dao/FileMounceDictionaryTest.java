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
        assertTrue(mounce.returnFileContent().size()>0);
    }
}
