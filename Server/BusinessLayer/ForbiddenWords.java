package BusinessLayer;

import DataAccessLayer.DForbiddenWordsController;
import DataAccessLayer.DTOs.DForbiddenWords;
import DataAccessLayer.DTOs.DTO;
import DataAccessLayer.DalController;

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
    private List<String> forbiddenWords;
    private List<DForbiddenWords> dForbiddenWords;
    private ForbiddenWords(){
        forbiddenWords = new ArrayList<>();
        dForbiddenWords = new ArrayList<>();
        DForbiddenWordsController dForbiddenWordsController = new DForbiddenWordsController();
        List<DTO> dForbiddenWords = dForbiddenWordsController.select();
        for(DTO dForbiddenWord: dForbiddenWords)
        {
            forbiddenWords.add(((DForbiddenWords)dForbiddenWord).getForbiddenWord());
            dForbiddenWords.add(dForbiddenWord);
        }
    }
    public void addForbiddenWord(String word)
    {
        forbiddenWords.add(word);
        new DForbiddenWords(forbiddenWords.size(), word).insert();
    }
    public void removeForbiddenWord(String word)
    {
        if(forbiddenWords.contains(word)) {
            dForbiddenWords.remove(forbiddenWords.indexOf(word)).delete();
            forbiddenWords.remove(word);
        }
    }
    public List<String> getForbiddenWords()
    {
        return forbiddenWords;
    }
}
