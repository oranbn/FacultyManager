package ServiceLayer.Objects.Operations.Client;

import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

public class AddQuestionOperation extends ClientOperation {
    private int questionId;
    private int examId;
    private int courseId;
    private int points;
    private String title;

    public AddQuestionOperation(short opCode) {
        super(opCode);
        this.questionId = -1;
        this.examId = -1;
        this.courseId = -1;
        this.points = -1;
        this.title = "";
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
            else if(points==-1)
                points = bytesToInt();
            else
                title = bytesToString();
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public int getPoints(){ return points;}
    public int getQuestionId(){return questionId;}
    public int getExamId(){return  examId;}
    public String getTitle(){ return title;}

    @Override
    public void execute(Protocol protocol) {
        protocol.addQuestion(this);
    }
}
