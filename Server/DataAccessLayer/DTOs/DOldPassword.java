package DataAccessLayer.DTOs;

import DataAccessLayer.DOldPasswordController;
import DataAccessLayer.DUserController;
import DataAccessLayer.DalController;

public class DOldPassword extends DTO{
    private boolean persisted;
    public static final String OldPasswordColumnName="Password";

    private String oldPassword;

    public DOldPassword(int id, String oldPassword) {
        super(new DOldPasswordController(), id);
        this.oldPassword = oldPassword;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DOldPasswordController)controller).insert(this);
            persisted = true;
        }
    }
    public String getOldPassword() {
        return oldPassword;
    }

}
