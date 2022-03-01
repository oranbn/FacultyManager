package DataAccessLayer;

import DataAccessLayer.DTOs.DChatMessage;
import DataAccessLayer.DTOs.DExamAnswer;
import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DExamAnswerController extends DalController {

    public DExamAnswerController() {
        super("ExamAnswer");
    }
    public boolean insert(DExamAnswer dExamAnswer)
    {
        String sql = "INSERT INTO ExamAnswer(DTO.IDColumnName,DExamAnswer.CourseIDColumnName, DExamAnswer.QuestionIdColumnName, DExamAnswer.ExamIdColumnName, DExamAnswer.ContentColumnName, DExamAnswer.CorrectColumnName) VALUES(?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dExamAnswer.getId());
            pstmt.setInt(2, dExamAnswer.getQuestionId());
            pstmt.setInt(3, dExamAnswer.getExamId());
            pstmt.setInt(4, dExamAnswer.getCourseId());
            pstmt.setString(5, dExamAnswer.getContent());
            pstmt.setBoolean(6, dExamAnswer.isCorrect());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean delete(DExamAnswer dExamAnswer)
    {
        String sql = "DELETE FROM ExamAnswers WHERE ID = ? AND QuestionId = ? AND ExamId = ? AND CourseId = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dExamAnswer.getId());
            pstmt.setInt(2, dExamAnswer.getQuestionId());
            pstmt.setInt(3, dExamAnswer.getExamId());
            pstmt.setInt(4, dExamAnswer.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DExamAnswer result = null;
        try {
            result = new DExamAnswer(reader.getInt(1), reader.getInt(2),reader.getInt(3), reader.getInt(4), reader.getString(5), reader.getBoolean(6));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
