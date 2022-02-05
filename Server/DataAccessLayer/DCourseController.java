package DataAccessLayer;

import DataAccessLayer.DTOs.DCourse;
import DataAccessLayer.DTOs.DTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DCourseController extends DalController{
    public DCourseController() {
        super("Course");
    }
    public boolean insert(DCourse course)
    {
        String sql = "INSERT INTO Course(DTO.IDColumnName, DCourse.NameColumnName,DCourse.GeneralInfoColumnName) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, course.getId());
            pstmt.setString(2, course.getName());
            pstmt.setString(3, course.getGeneralInfo());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DCourse result = null;
        try {
            result = new DCourse(reader.getInt(1), reader.getString(2),reader.getString(3));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
