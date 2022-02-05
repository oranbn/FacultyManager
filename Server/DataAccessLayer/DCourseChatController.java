package DataAccessLayer;

import DataAccessLayer.DTOs.DCourseChat;
import DataAccessLayer.DTOs.DTO;
import DataAccessLayer.DTOs.DUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DCourseChatController extends DalController{
    public DCourseChatController() {
        super("CourseChat");
    }
    public boolean insert(DCourseChat courseChat)
    {

        String sql = "INSERT INTO CourseChat(DTO.IDColumnName,DCourseChat.CourseIDColumnName,DCourseChat.ChatNameColumnName) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseChat.getId());
            pstmt.setInt(2, courseChat.getCourseId());
            pstmt.setString(3, courseChat.getChatName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        return null;
    }
}
