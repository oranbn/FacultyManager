package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class CreateCourseOperation extends ClientOperation {
    private String name;
    private String generalInfo;

    public CreateCourseOperation(short opCode) {
        super(opCode);
        this.name = "";
        this.generalInfo = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(name.equals("")){
                name = bytesToString();
            }
            else if(generalInfo.equals("")){
                this.generalInfo = bytesToString();
            }
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public String getName() {
        return name;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.createCourse(this);
    }
}
