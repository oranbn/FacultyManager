package BusinessLayer;

import DataAccessLayer.DTOs.DUser;
import DataAccessLayer.DUserController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserController {
    private final ConcurrentHashMap<String, User> users;
    private final ConcurrentHashMap<String, List<String>> friendship;
    private final DUserController dUserController;
    private int id = 0;
    private final LoggedInUsers loggedInUsers;
    private final int maxPasswordLength = 20;
    private final int minPasswordLength = 8;
    private final List<String> forbiddenPasswords;

    public UserController(LoggedInUsers loggedInUsers) {
        this.loggedInUsers = loggedInUsers;
        users = new ConcurrentHashMap<>();
        friendship = new ConcurrentHashMap<>();
        dUserController = new DUserController();
        forbiddenPasswords = new ArrayList<>();
    }

    // Load all users from database
    public void LoadData() {
         List<DUser> dusers = dUserController.selectAllUsers();
         for(DUser duser : dusers)
         {
             // get oldpassword of the user
             // create new user
             // set persisted true
             // id++;
         }
    }


    private boolean isLegalPassword(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
        }
        return !(password.length() < minPasswordLength | password.length() > maxPasswordLength | !capitalFlag | !lowerCaseFlag | !numberFlag | forbiddenPasswords.contains(password));
    }

    private boolean isUniqueEmail(String email) {
        return !users.containsKey(email);
    }

    private boolean isLegalEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean changePassword(String email, String password) throws Exception {
        if(isLegalPassword(password))
            if(users.get(email).isPasswordOld(password))
                throw new Exception("Changing to old password is not allowed!");
            else if(users.get(email).validatePassMatch(password))
                throw new Exception("Changing to same password is not allowed!");
            else
                users.get(email).changePassword(password);
        else
            throw new Exception("New password is not allowed!");
        return true;
    }

    public boolean register(String email, String firstName, String lastName, String idNumber, String phoneNumber, String password, String birthday) {
        if(isLegalEmail(email) && isUniqueEmail(email) && isLegalPassword(password)) {
            users.put(email, new User(email, firstName, lastName, idNumber, phoneNumber, password, birthday, new DUser(id++, email, firstName, lastName, idNumber, phoneNumber, password,1,false, birthday)));
            return true;
        }

        return false;
    }

    public void login(String email, String password, int connectionId) throws Exception {
        if(users.containsKey(email))
            if(users.get(email).validatePassMatch(password))
                if (!loggedInUsers.isLoggedIn(email)) {
                    users.get(email).setConnectionId(connectionId);
                    loggedInUsers.add(email);
                }
                else
                    throw new Exception("user is already logged in!");
            else
                throw new Exception("email or password is incorrect");
        else
            throw new Exception("email or password is incorrect");
    }

    public void logout(String email) throws Exception {
        if(users.containsKey(email))
            if(loggedInUsers.isLoggedIn(email))
            {
              users.get(email).setConnectionId(-1);
              loggedInUsers.remove(email);
            }
        else
                throw new Exception("User is already logged out!");
        else
            throw new Exception("email is incorrect");
    }
    public void sendFriendRequest(String sender, String recipient){

    }
    public void cancelFriendRequest(String sender, String recipient)
    {

    }
    public void acceptFriendRequest(String sender, String recipient)
    {

    }
    public void cancelFriendship(String sender, String recipient)
    {

    }
    public boolean sendPrivateMessage(String sender, String recipient, String content, String dateAndTime)
    {
        return false;
    }
}
