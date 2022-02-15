package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class RegisterOperation extends ClientOperation {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String birthday;

    public RegisterOperation(short opCode) {
        super(opCode);
        this.userName= "";
        this.password = "";
        this.birthday = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte==(byte)0)
        {
            if(getUserName().equals("")){
                this.userName = bytesToString();
            }
            else if(password.equals("")){
                this.password = bytesToString();
            }
            else if(firstName.equals("")){
                this.firstName = bytesToString();
            }
            else if(lastName.equals("")){
                this.lastName = bytesToString();
            }
            else if(idNumber.equals("")){
                this.idNumber = bytesToString();
            }
            else if(phoneNumber.equals("")){
                this.phoneNumber = bytesToString();
            }
            else if(birthday.equals("")){
                this.birthday = bytesToString();
            }
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getUserName() {
        return userName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.register(this);
    }
}
