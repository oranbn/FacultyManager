package DataAccessLayer.DTOs;

import DataAccessLayer.DUserController;
import DataAccessLayer.DalController;

public class DUser extends DTO {
    private boolean persisted;
    public static final String EmailColumnName="Email";
    public static final String PasswordColumnName="Password";
    public static final String FirstNameColumnName="FirstName";
    public static final String LastNameColumnName="LastName";
    public static final String IDNumberColumnName="IDNumber";
    public static final String PhoneNumberColumnName="PhoneNumber";
    public static final String BirthdayColumnName="Birthday";
    public static final String PermissionLevelColumnName="PermissionLevel";
    public static final String IsEmailApprovedColumnName="IsEmailApproved";

    private String email;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String password;
    private int permissionLevel;
    private boolean isEmailApproved;
    private String birthday;


    public DUser(int id, String email, String firstName, String lastName, String idNumber, String phoneNumber, String password, int permissionLevel, boolean isEmailApproved, String birthday) {
        super(new DUserController(), id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.permissionLevel = permissionLevel;
        this.isEmailApproved = isEmailApproved;
        this.birthday = birthday;
    }
    public void insert()
    {
        if(!persisted)
        {
            ((DUserController)controller).insert(this);
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
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }
    public int getPermissionLevel() {
        return permissionLevel;
    }
    public boolean isEmailApproved() {
        return isEmailApproved;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPassword(String password) {
        this.password = password;
        if(persisted)
        {
            controller.update(getId(), PasswordColumnName, password);
        }
    }
    public void approveEmail() {
        isEmailApproved = true;
        if(persisted)
        {
            controller.update(getId(), PasswordColumnName, isEmailApproved);
        }
    }
}
