package DataAccessLayer.DTOs;

import DataAccessLayer.DalController;

public class DUser extends DTO {
    private boolean persisted;
    public static final String EmailColumnName="Email";
    public static final String PasswordColumnName="Password";
    public static final String BirthdayColumnName="Birthday";

    private String email;
    private String password;
    private String birthday;

    public DUser(DalController controller, int id, String email, String password, String birthday) {
        super(controller, id);
        this.email = email;
        this.password = password;
        this.birthday = birthday;
    }
    public void Insert()
    {
        if(!persisted)
        {
            // controller.insert(this);
            persisted = true;
        }
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthday() {
        return birthday;
    }
}
