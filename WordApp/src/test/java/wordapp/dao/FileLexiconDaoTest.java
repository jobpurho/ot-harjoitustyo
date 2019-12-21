package wordapp.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;


public class FileLexiconDaoTest {
    
    FileLexiconDao fldaoA;
    FileLexiconDao fldaoB;
    
    public FileLexiconDaoTest() {
        OriginalLexicon lexicon = new FileMounceDictionary("dictionary.txt");
        
        fldaoA = new FileLexiconDao("test.ser");
        fldaoA.setFileContent(lexicon.getFileContent());
        fldaoA.save();
        fldaoB = new FileLexiconDao("test.ser");
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
    public void fileContentIsCorrectWhenSavedAndLoaded() {     
        assertEquals(fldaoA.returnFileContent().size(),fldaoB.returnFileContent().size());
    }  
}
