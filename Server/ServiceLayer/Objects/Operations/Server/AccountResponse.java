package ServiceLayer.Objects.Operations.Server;

import ServiceLayer.Objects.ServerOperation;
import ServiceLayer.Protocol;

public class AccountResponse extends ServerOperation {
    private String email;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String phoneNumber;
    private String birthday;

    public AccountResponse(short opCode, String email, String firstName, String lastName, String idNumber, String phoneNumber, String birthday) {
        super(opCode);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }


    public byte[] encode() {
        byte[] bytes = new byte[email.length() + firstName.length() + lastName.length() + idNumber.length() + phoneNumber.length() + phoneNumber.length() + 9];
        bytes[0] = (byte)((getOpCode() >> 8) & 0xFF);
        bytes[1] = (byte)(getOpCode() & 0xFF);
        int index = 2;
        index = AddStringToByteArray(email, bytes, index);
        bytes[index++] = 0;
        index = AddStringToByteArray(firstName, bytes, index);
        bytes[index++] = 0;
        index = AddStringToByteArray(lastName, bytes, index);
        bytes[index++] = 0;
        index = AddStringToByteArray(idNumber, bytes, index);
        bytes[index++] = 0;
        index = AddStringToByteArray(phoneNumber, bytes, index);
        bytes[index++] = 0;
        index = AddStringToByteArray(birthday, bytes, index);
        bytes[index++] = 0;
        bytes[index] = (byte)';';
        return bytes;
    }

    @Override
    public void execute(Protocol protocol) {

    }
}
