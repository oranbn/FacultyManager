package BusinessLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MultipleChoiceQuestion extends ExamQuestion{
    private final List<Integer> correctAnswers;
    private final ConcurrentHashMap<Integer, String> answers;
    public MultipleChoiceQuestion(int points, String title, List<Integer> correctAnswers, ConcurrentHashMap<Integer, String> answers) {
        super(points, title);
        this.correctAnswers = correctAnswers;
        this.answers = answers;
    }
    public void addCorrectAnswer(int answerId)
    {
        correctAnswers.add(answerId);
    }
    public void removeCorrectAnswer(int answerId)
    {
        correctAnswers.remove(answerId);
    }
    public void addAnswer(String answer, int answerId)
    {
        answers.put(answerId, answer);
    }
    public List<Integer> getCorrectAnswers()
    {
        return correctAnswers;
    }
}
