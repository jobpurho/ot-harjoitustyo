package wordapp.dao;

import wordapp.domain.Lexicon;
import java.util.HashMap;

public interface LexiconDao {
    HashMap returnFileContent();
    
    void save();
    
    void setFileContent(HashMap fileContent);
    
    void removeFile();
}
