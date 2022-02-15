package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class RemoveAnswerOperation extends ClientOperation {
    private int questionId;
    private int answerId;
    private int examId;
    private int courseId;

    public RemoveAnswerOperation(short opCode) {
        super(opCode);
        questionId = -1;
        answerId = -1;
        examId = -1;
        courseId = -1;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(questionId==-1)
                questionId = bytesToInt();
            else if(answerId==-1)
                answerId = bytesToInt();
            else if(examId==-1)
                examId = bytesToInt();
            else
                courseId = bytesToInt();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getQuestionId() { return questionId; }
    public int getAnswerId(){return answerId;}
    public int getExamId(){return examId;}
    public int getCourseId(){ return courseId; }

    @Override
    public void execute(Protocol protocol) {
        protocol.removeAnswer(this);
    }
}
