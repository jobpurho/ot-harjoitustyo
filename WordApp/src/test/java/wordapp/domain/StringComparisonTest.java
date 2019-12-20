package wordapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringComparisonTest {
    
    private StringComparison comp;
    
    public StringComparisonTest() {
        comp = new StringComparison();
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

    @ Test
    public void isSimilarEnoughIfEditDistanceIs2() {
        assertTrue(comp.isSimilarEnough("approve", "apbrofe"));
        assertTrue(comp.isSimilarEnough("benefactor", "befenactor"));
        assertTrue(comp.isSimilarEnough("cheerful", "chearfull"));
        assertTrue(comp.isSimilarEnough("immediately", "immadiatly"));
    }

    @ Test
    public void isNotSimilarEnoughIfEditDistanceIs3() {
        assertTrue(!comp.isSimilarEnough("approve", "apbrofes"));
        assertTrue(!comp.isSimilarEnough("benefactor", "befenactorr"));
        assertTrue(!comp.isSimilarEnough("cheerful", "cearfull"));
        assertTrue(!comp.isSimilarEnough("immediately", "immadietly"));        
    }    
    
    @Test
    public void removeArticleReturnsCorrectStrings() {
        assertEquals(comp.removeArticle("the word"), "word");
        assertEquals(comp.removeArticle("a word"), "word");
        assertEquals(comp.removeArticle("an apple"), "apple");
        assertEquals(comp.removeArticle("to think"), "think");
    }

    @Test
    public void removeFirstReturnsCorrectStrings() {
        assertEquals(comp.removeFirst("this word"), "word");
    }    

    @ Test
    public void isSimilarEnoughIfArticleOrTo() {
        assertTrue(comp.isSimilarEnough("a human being", "human being"));
        assertTrue(comp.isSimilarEnough("human being", "a human being"));
        assertTrue(comp.isSimilarEnough("to give", "give"));
        assertTrue(comp.isSimilarEnough("give", "to give"));
    }

    @ Test
    public void isSimilarEnoughIfArticleAndEditDistanceIs2() {
        assertTrue(comp.isSimilarEnough("hunam being", "a human being"));
    }

    @ Test
    public void isNotSimilarEnoughIfArticleAndEditDistanceIs3() {
        assertTrue(!comp.isSimilarEnough("hunam bing", "a human being"));
    }
    
    @ Test
    public void isSimilarEnoughIfArticleNotValidAndEditDistanceIs2() {
        assertTrue(comp.isSimilarEnough("en hunam being", "a human being"));
    }

    @ Test
    public void isNotSimilarEnoughIfArticleNotValidAndEditDistanceIs2() {
        assertTrue(!comp.isSimilarEnough("en hunam bing", "a human being"));
    } 

    @ Test
    public void isSimilarEnoughAndNoDifferenceWithoutTo() {
        assertTrue(comp.isSimilarEnough("be able", "to be able"));
        assertTrue(!comp.different());
    } 
    public void differentIsTrueWhenMistakeOnlyInArticle() {
        assertTrue(comp.isSimilarEnough("en human being", "a human being"));
        assertTrue(comp.different());
    } 
}
