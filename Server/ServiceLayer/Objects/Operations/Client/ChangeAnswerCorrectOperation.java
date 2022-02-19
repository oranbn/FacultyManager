package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class ChangeAnswerCorrectOperation extends ClientOperation {
    private int answerId;
    private int questionId;
    private int examId;
    private int courseId;
    private boolean correct;

    public ChangeAnswerCorrectOperation(short opCode) {
        super(opCode);
        this.answerId = -1;
        this.questionId = -1;
        this.examId = -1;
        this.courseId = -1;
        this.correct = false;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(answerId==-1)
                answerId = bytesToInt();
            else if(questionId==-1)
                questionId = bytesToInt();
            else if(examId==-1)
                examId = bytesToInt();
            else if(courseId==-1)
                courseId = bytesToInt();
            else
                correct = bytesToBoolean();
        }
        else
            pushNextByte(nextByte);
        return false;
    }
    public int getAnswerId(){return answerId;}
    public int getCourseId(){ return courseId;}
    public int getQuestionId(){return questionId;}
    public int getExamId(){return  examId;}
    public boolean getCorrect(){ return correct;}

    @Override
    public void execute(Protocol protocol) {
        protocol.changeAnswerCorrect(this);
    }
}
