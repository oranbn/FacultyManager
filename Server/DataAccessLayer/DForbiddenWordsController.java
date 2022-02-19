package DataAccessLayer;

import DataAccessLayer.DTOs.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DForbiddenWordsController extends DalController {

    public DForbiddenWordsController() {
        super("ForbiddenWords");
    }
    public boolean insert(DForbiddenWords dForbiddenWords)
    {
        String sql = "INSERT INTO ForbiddenWords(DTO.IDColumnName,DForbiddenWords.ForbiddenWordsColumnName) VALUES(?,?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dForbiddenWords.getId());
            pstmt.setString(2, dForbiddenWords.getForbiddenWord());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean delete(DForbiddenWords dForbiddenWords)
    {
        String sql = "DELETE FROM ExamQuestions WHERE ID = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dForbiddenWords.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DForbiddenWords result = null;
        try {
            result = new DForbiddenWords(reader.getInt(1), reader.getString(2));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
