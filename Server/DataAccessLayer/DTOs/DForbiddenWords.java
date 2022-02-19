package DataAccessLayer.DTOs;

import DataAccessLayer.*;

public class DForbiddenWords extends DTO{
    public static final String ForbiddenWordsColumnName="ForbiddenWords";

    private boolean persisted;

    private String forbiddenWord;

    public DForbiddenWords(int id, String forbiddenWord) {
        super(new DForbiddenWordsController(), id);
        this.forbiddenWord = forbiddenWord;
        persisted = false;
    }

    public String getForbiddenWord() {
        return forbiddenWord;
    }

    public void insert()
    {
        if(!persisted)
        {
            ((DForbiddenWordsController)controller).insert(this);
            persisted = true;
        }
    }

    public void delete() {
        if(persisted)
        {
            ((DForbiddenWordsController)controller).delete(this);
        }
    }
}
