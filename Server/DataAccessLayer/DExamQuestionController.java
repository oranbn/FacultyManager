package DataAccessLayer;

import DataAccessLayer.DTOs.DChatMessage;
import DataAccessLayer.DTOs.DExamAnswer;
import DataAccessLayer.DTOs.DExamQuestion;
import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DExamQuestionController extends DalController {

    public DExamQuestionController() {
        super("ExamQuestions");
    }
    public boolean insert(DExamQuestion dExamAnswer)
    {
        String sql = "INSERT INTO ExamQuestions(DTO.IDColumnName,DExamQuestion.ExamIdColumnName, DExamQuestion.CourseIDColumnName, DExamQuestion.PointsColumnName,DExamQuestion.TitleColumnName) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dExamAnswer.getId());
            pstmt.setInt(2, dExamAnswer.getExamId());
            pstmt.setInt(3, dExamAnswer.getCourseId());
            pstmt.setDouble(4, dExamAnswer.getPoints());
            pstmt.setString(5, dExamAnswer.getTitle());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean delete(DExamQuestion dExamQuestion)
    {
        String sql = "DELETE FROM ExamQuestions WHERE ID = ? AND ExamId = ? AND CourseId = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dExamQuestion.getId());
            pstmt.setInt(2, dExamQuestion.getExamId());
            pstmt.setInt(3, dExamQuestion.getCourseId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DExamQuestion result = null;
        try {
            result = new DExamQuestion(reader.getInt(1), reader.getInt(2),reader.getInt(3), reader.getDouble(4), reader.getString(5));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
