package BusinessLayer;

import DataAccessLayer.DTOs.DUser;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String email;
    private String password;
    private String birthday;
    private boolean loggedin;
    private final List<String> oldPasswords;
    private DUser dUser;
    // user constructor for client register request:
    public User(String email, String password, String birthday, DUser dUser)
    {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.loggedin = false;
        this.oldPasswords = new ArrayList<>();
        this.dUser = dUser;
        //dUser.insert(); - insert the new user to the database;
    }
    // user constructor - load database users:
    public User(DUser u, List<String> OldPassword)
    {
        dUser = u;
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.birthday = u.getBirthday();
        this.oldPasswords = OldPassword;
        this.loggedin = false;
    }
    public void ChangePassword(String newPassword)
    {
        oldPasswords.add(password);
        // new DOldPassword(dUser.Id, password).insert(); - insert old password to database
        this.password = newPassword;
    }
    public boolean validatePassMatch(String password)
    {
        return password.equals(this.password);
    }
}
