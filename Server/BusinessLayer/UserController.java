package BusinessLayer;

import DataAccessLayer.DUserController;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private final ConcurrentHashMap<String, User> users;
    private final DUserController dUserController;
    private int id = 0;
    private final LoggedInUsers loggedInUsers;
    private final int maxPasswordLength = 20;
    private final int minPasswrodLength = 8;
    private List<String> forbiddenPassword;

    public UserController(LoggedInUsers loggedInUsers) {
        this.loggedInUsers = loggedInUsers;
        users = new ConcurrentHashMap<>();
        dUserController = new DUserController();
    }

    // Load all users from database
    public void LoadData() {
        // List<DUser> dusers = dUserController.SelectAllUsers();
        // foreach duser
        // get oldpassword of the user
        // create new user
        // set persisted true
        // id++;
    }

    private boolean isLegalPassword(String password) {
        return false;
    }

    private boolean isUniqueEmail(String email) {
        return false;
    }

    private boolean isLegalEmail(String email) {
        return false;
    }

    public boolean changePassword(String email, String password) {
        return false;
    }

    public boolean register(String email, String password, String birthday) {
        return false;
    }

    public boolean login(String email, String password) {
        return false;
    }

    public boolean logout(String email) {
        return false;
    }

    private boolean privateMessage(String sender, String recipient)
    {
        return false;
    }
}
