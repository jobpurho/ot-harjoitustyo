package wordapp.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.*;
import static org.junit.Assert.*;


public class FileLexiconDaoTest {
    
    FileLexiconDao fldao;
    
    public FileLexiconDaoTest() {
        fldao = new FileLexiconDao("test.ser");
        OriginalLexicon lexicon = new FileOriginalLexicon("file.txt");
        fldao.setFileContent(lexicon.getFileContent());
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
    
}
