package DataAccessLayer;

import DataAccessLayer.DTOs.DOldPassword;
import DataAccessLayer.DTOs.DTO;
import DataAccessLayer.DTOs.DUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DOldPasswordController extends DalController{
    public DOldPasswordController() {
        super("OldPassword");
    }
    public boolean insert(DOldPassword password)
    {
        String sql = "INSERT INTO OldPassword("+DTO.IDColumnName+","+DOldPassword.OldPasswordColumnName+") VALUES(?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, password.getId());
            pstmt.setString(2, password.getOldPassword());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public List<String> selectOldPasswords(int id)
    {
        List<String> results = new ArrayList<>();
        String sql = "SELECT OldPassword.Password from OldPassword INNER JOIN User ON OldPassword.ID = User.ID AND OldPassword.ID="+id+";";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                results.add(((DOldPassword)ConvertReaderToObject(rs)).getOldPassword());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return results;
    }
    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DOldPassword result = null;
        try {
            result = new DOldPassword(reader.getInt(1), reader.getString(2));}
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
