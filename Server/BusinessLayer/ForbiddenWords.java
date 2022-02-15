package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

    public class ForbiddenWords {
        private static class ForbiddenWordsHolder{
            private final static ForbiddenWords instance = new ForbiddenWords();
        }
        public static ForbiddenWords getInstance() {
            return ForbiddenWordsHolder.instance;
        }
        List<String> forbiddenWords;
        private ForbiddenWords(){
            forbiddenWords = new ArrayList<>();
            // load words from database if exist
        }
        public void addForbiddenWord(String word)
        {
            forbiddenWords.add(word);
            // add word to database
        }
        public void removeForbiddenWord(String word)
        {
            forbiddenWords.remove(word);
            // remove word from database
        }
        public List<String> getForbiddenWords()
        {
            return forbiddenWords;
        }
}
