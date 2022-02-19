package BusinessLayer;

import DataAccessLayer.DTOs.DUser;
import DataAccessLayer.DUserController;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
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
    public void loadData() {
        List<DUser> dusers = dUserController.selectAllUsers();
        for(DUser duser : dusers)
        {
            // get oldpassword of the user
            // create new user
            // set persisted true
            // id++;
        }
    }


    private void isLegalPassword(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        for (int i = 0; i < password.length() && (!capitalFlag || !lowerCaseFlag || !numberFlag) ; i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
        }
        if(password.length() < minPasswordLength | password.length() > maxPasswordLength | !capitalFlag | !lowerCaseFlag | !numberFlag | forbiddenPasswords.contains(password))
            throw new IllegalArgumentException("Illegal password");
    }

    private void isUniqueEmail(String email) {
        if(users.containsKey(email))
            throw new IllegalArgumentException("Email is already exist");
    }

    private void isLegalEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        if(!m.matches())
            throw new IllegalArgumentException("Illegal email address");
    }

    public void changePassword(String email, String password) throws Exception {
        isLegalPassword(password);
        if(users.get(email).isPasswordOld(password))
            throw new Exception("Changing to old password is not allowed!");
        else if(users.get(email).validatePassMatch(password))
            throw new Exception("Changing to same password is not allowed!");
        else
            users.get(email).changePassword(password);
    }

    public void register(String email, String firstName, String lastName, String idNumber, String phoneNumber, String password, String birthday) {
        isLegalEmail(email);
        isUniqueEmail(email);
        isLegalPassword(password);
        User user = new User(email, firstName, lastName, idNumber, phoneNumber, password, birthday, new DUser(id++, email, firstName, lastName, idNumber, phoneNumber, password,1,false, birthday));
        sendEmail(user);
        users.put(email, user);
    }

    public User login(String email, String password, int connectionId) throws Exception {
        if(users.containsKey(email))
            if(users.get(email).validatePassMatch(password))
                if (!loggedInUsers.isLoggedIn(email)) {
                    users.get(email).setConnectionId(connectionId);
                    loggedInUsers.add(email);
                    return users.get(email);
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
    public void sendPrivateMessage(String sender, String recipient, String content, String dateAndTime)
    {

    }

    public void sendEmail(User user) {
        String to = user.getEmail();
        String from = "FacultyManagerMail@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Faculty Manager registration verification");
            int randomCode = new Random().nextInt(900000) + 100000;
            user.setActivationCode(randomCode);
            message.setText("Hello "+user.getFirstName()+" "+user.getLastName()+", and welcome to Faculty Manager.\n your activation code is: "+randomCode);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
    public void sendEmailForgotPassword(String email)
    {
        User user = users.get(email);
        if(user == null)
            return;
        String to = email;
        String from = "FacultyManagerMail@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Faculty Manager registration verification");
            int randomCode = new Random().nextInt(900000) + 100000;
            user.setForgotPassword(randomCode);
            message.setText("Hello "+user.getFirstName()+" "+user.getLastName()+", the code for resetting your password is: "+randomCode);
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public void changeUserPermission(User user, String email, int permission) {
        // check if user can change another user permission
        User u = users.get(email);
        if(u == null)
            throw new IllegalArgumentException("Invalid email");
        u.setPermissionLevel(permission);
    }
}
