package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;

public class RegisterOperation extends ClientOperation {
    private String userName;
    private String password;
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

}
