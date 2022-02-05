package DataAccessLayer;

import DataAccessLayer.DTOs.DCourseChat;
import DataAccessLayer.DTOs.DTO;
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
        String sql = "INSERT INTO CourseChat(DTO.IDColumnName,DCourseChat.CourseIDColumnName,DCourseChat.ChatNameColumnName, DCourseChat.PinMessageColumnName) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseChat.getId());
            pstmt.setInt(2, courseChat.getCourseId());
            pstmt.setString(3, courseChat.getChatName());
            pstmt.setString(4, courseChat.getPinMessage());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DCourseChat result = null;
        try {
            result = new DCourseChat(reader.getInt(1), reader.getInt(2),reader.getString(3),reader.getString(4));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
