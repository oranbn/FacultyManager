package ServiceLayer.Objects.Operations.Client;

import BusinessLayer.ExamAnswer;
import BusinessLayer.PreExamAnswer;
import ServiceLayer.Objects.ClientOperation;
import ServiceLayer.Protocol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AddQuestionOperation extends ClientOperation {
    private int examId;
    private int courseId;
    private double points;
    private String title;
    private final List<PreExamAnswer> answers;
    private String content;
    private boolean correct;

    public AddQuestionOperation(short opCode) {
        super(opCode);
        this.examId = -1;
        this.courseId = -1;
        this.points = -1;
        this.title = "";
        this.answers = new ArrayList();
        this.content ="";
        this.correct = false;
    }

    @Override
    public boolean pushByte(byte nextByte) {
        if(nextByte == ';')
            return true;
        if(nextByte=='\0')
        {
            if(examId==-1)
                examId = bytesToInt();
            else if(courseId==-1)
                courseId = bytesToInt();
            else if(points==-1)
                points = bytesToDouble();
            else if(title.equals(""))
                title = bytesToString();
            else if(content.equals(""))
                content = bytesToString();
            else
            {
                correct = bytesToBoolean();
                answers.add(new PreExamAnswer(content, correct));
                content = "";
            }
        }
        else
            pushNextByte(nextByte);
        return false;
    }

    public int getCourseId(){ return courseId;}
    public double getPoints(){ return points;}
    public int getExamId(){return  examId;}
    public String getTitle(){ return title;}

    public List<PreExamAnswer> getAnswers() {
        return answers;
    }

    @Override
    public void execute(Protocol protocol) {
        protocol.addQuestion(this);
    }
}
