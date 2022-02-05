package DataAccessLayer;

import DataAccessLayer.DTOs.DCourse;
import DataAccessLayer.DTOs.DExam;
import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DExamController extends DalController{
    public DExamController() {
        super("Exam");
    }
    public boolean insert(DExam exam)
    {
        String sql = "INSERT INTO Exam(DTO.IDColumnName, DExam.CourseIDColumnName, DExam.DurationColumnName,DExam.IsOpenColumnName,DExam.ExamDateColumnName) VALUES(?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, exam.getId());
            pstmt.setInt(2, exam.getCourseId());
            pstmt.setInt(3, exam.getDuration());
            pstmt.setBoolean(4, exam.isOpen());
            pstmt.setString(5, exam.getExamDate());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DExam result = null;
        try {
            result = new DExam(reader.getInt(1), reader.getInt(2),reader.getInt(3),reader.getBoolean(4),reader.getString(5));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}