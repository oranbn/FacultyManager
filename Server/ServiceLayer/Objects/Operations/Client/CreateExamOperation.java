package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class CreateExamOperation extends ClientOperation {
    private int courseId;
    private int duration;
    private String examDate;

    public CreateExamOperation(short opCode) {
        super(opCode);
        this.courseId = -1;
        this.duration = -1;
        this.examDate = "";
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(courseId==-1)
                courseId = bytesToInt();
            else if(duration==-1)
                duration = bytesToInt();
            else
                examDate = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public int getDuration(){ return duration;}
    public String getExamDate(){ return examDate;}

    @Override
    public void execute(Protocol protocol) {
        protocol.createExam(this);
    }
}
