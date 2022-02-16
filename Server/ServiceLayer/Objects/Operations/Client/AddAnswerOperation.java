package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class AddAnswerOperation extends ClientOperation {
    private int questionId;
    private int examId;
    private int courseId;
    private String content;
    private boolean correct;

    public AddAnswerOperation(short opCode) {
        super(opCode);
        this.questionId = -1;
        this.examId = -1;
        this.courseId = -1;
        this.content = "";
        this.correct = false;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(questionId==-1)
                questionId = bytesToInt();
            else if(examId==-1)
                examId = bytesToInt();
            else if(courseId==-1)
                courseId = bytesToInt();
            else if(content.equals(""))
                content = bytesToString();
            else
                correct = bytesToBoolean();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public int getQuestionId(){return questionId;}
    public int getExamId(){return  examId;}
    public String getContent(){ return content;}
    public boolean isCorrect(){return correct;}

    @Override
    public void execute(Protocol protocol) {
        protocol.addAnswer(this);
    }
}
