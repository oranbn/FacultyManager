package ServiceLayer.Objects;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public abstract class ClientOperation implements Operation {
    private final short opCode;
    private int length;
    byte[] bArr;
    private final int size=12;

    public ClientOperation(short opCode) {
        this.opCode = opCode;
        bArr = new byte[size];
        this.length = 0;
    }
    public short getOpCode() {
        return opCode;
    }

    public String getMessage() {
        return null;
    }
    public void pushNextByte(byte nextByte) {
        if (length >= bArr.length) {
            bArr = Arrays.copyOf(bArr, length * 2);
        }
        bArr[length++] = nextByte;
    }
    public abstract boolean pushByte(byte nextByte);
    public String bytesToString(){
        String output = new String(bArr, 0 , length, StandardCharsets.UTF_8);
        bArr = new byte[size];
        length = 0;
        return output;
    }
    public int bytesToInt(){
        int output = ByteBuffer.wrap(bArr).getInt();
        bArr = new byte[size];
        length = 0;
        return output;
    }
    public double bytesToDouble(){
        double output = ByteBuffer.wrap(bArr).getDouble();
        bArr = new byte[size];
        length = 0;
        return output;
    }
    public boolean bytesToBoolean(){
        bArr = new byte[size];
        int temp = length;
        length = 0;
        if(temp > 1 || bArr[0] == 1) {
            return false;
        }
        return true;
    }
    public int getLength() {
        return length;
    }
    public byte[] encode(){ return null;}
}