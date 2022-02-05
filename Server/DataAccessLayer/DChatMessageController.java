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
        String sql = "INSERT INTO ChatMessage(DTO.IDColumnName,DChatMessage.UserSenderColumnName,DChatMessage.TimeColumnName,DChatMessage.ContentColumnName,DChatMessage.MarkColumnName) VALUES(?,?,?,?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, chatMessage.getId());
            pstmt.setString(2, chatMessage.getUserSender());
            pstmt.setString(3, chatMessage.getTime());
            pstmt.setString(4, chatMessage.getContent());
            pstmt.setBoolean(5, chatMessage.isMark());
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
            result = new DChatMessage(reader.getInt(1), reader.getString(2), reader.getString(3), reader.getString(4), reader.getBoolean(5));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
