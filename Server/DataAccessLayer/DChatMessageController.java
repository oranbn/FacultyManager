package DataAccessLayer;

import DataAccessLayer.DTOs.DChatMessage;
import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DChatMessageController extends DalController {

    public DChatMessageController() {
        super("ChatMessage");
    }
    public boolean insert(DChatMessage chatMessage)
    {
        String sql = "INSERT INTO ChatMessage(DTO.IDColumnName,DChatMessage.CourseIdColumnName,DChatMessage.MessageIdColumnName,DChatMessage.UserSenderColumnName,DChatMessage.TimeColumnName,DChatMessage.ContentColumnName,DChatMessage.MarkColumnName) VALUES(?,?,?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getId());
            pstmt.setInt(2, chatMessage.getCourseId());
            pstmt.setInt(3, chatMessage.getMessageId());
            pstmt.setString(4, chatMessage.getUserSender());
            pstmt.setString(5, chatMessage.getTime());
            pstmt.setString(6, chatMessage.getContent());
            pstmt.setBoolean(7, chatMessage.isMark());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean delete(DChatMessage chatMessage)
    {
        String sql = "DELETE FROM ChatMessage WHERE MessageId = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getMessageId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DChatMessage result = null;
        try {
            result = new DChatMessage(reader.getInt(1), reader.getInt(2),reader.getInt(3), reader.getString(4), reader.getString(5), reader.getString(6), reader.getBoolean(7));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
