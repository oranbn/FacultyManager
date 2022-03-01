package BusinessLayer;

import DataAccessLayer.DTOs.DOldPassword;
import DataAccessLayer.DTOs.DUser;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String email;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String password;
    private String birthday;
    private int permissionLevel;
    private boolean isEmailApproved;
    private int connectionId;
    private int activationCode;
    private int forgotPassword;
    private final List<String> oldPasswords;
    private DUser dUser;
    // user constructor for client register request:
    public User(String email, String password, String firstName, String lastName, String idNumber, String phoneNumber, String birthday, DUser dUser)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birthday = birthday;
        this.oldPasswords = new ArrayList<>();
        this.dUser = dUser;
        this.permissionLevel = 1;
        this.connectionId = -1;
        this.forgotPassword = -1;
        this.isEmailApproved = false;
        dUser.insert();
    }
    // user constructor - load database users:
    public User(DUser u, List<String> OldPassword)
    {
        dUser = u;
        this.email = u.getEmail();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.idNumber = u.getIdNumber();
        this.phoneNumber = u.getPhoneNumber();
        this.password = u.getPassword();
        this.birthday = u.getBirthday();
        this.permissionLevel = u.getPermissionLevel();
        this.isEmailApproved = u.isEmailApproved();
        this.oldPasswords = OldPassword;
        this.connectionId = -1;
    }
    public void changePassword(String newPassword)
    {
        oldPasswords.add(password);
        new DOldPassword(dUser.getId(), password).insert();
        dUser.setPassword(newPassword);
        this.password = newPassword;
    }
    public boolean validatePassMatch(String password)
    {
        return password.equals(this.password);
    }
    public boolean isPasswordOld(String password)
    {
        return oldPasswords.contains(password);
    }
    public void setConnectionId(int connectionId)
    {
        this.connectionId = connectionId;
    }
    public void changePermissionLevel(int permissionLevel){
        this.permissionLevel = permissionLevel;
    }
    public void approveEmail()
    {
        isEmailApproved = true;
        dUser.approveEmail();
    }
    public void setActivationCode(int activationCode){ this.activationCode = activationCode; dUser.setActivationCode(activationCode);}
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public boolean isEmailApproved() {
        return isEmailApproved;
    }

    public int getConnectionId() {
        return connectionId;
    }
    public int getActivationCode() {
        return activationCode;
    }
    public int getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(int forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
    public void setPermissionLevel(int permissionLevel)
    {
        this.permissionLevel = permissionLevel;
    }

    public void activateAccount(String activationCode) {
        try {
            int code = Integer.parseInt(activationCode);
            if(code == -1 || code != this.activationCode)
                throw new IllegalArgumentException("Invalid activation code");
            isEmailApproved = true;
            dUser.approveEmail();
        }
        catch (NumberFormatException  e)
        {
            throw new IllegalArgumentException("Invalid activation code");
        }

    }
}
