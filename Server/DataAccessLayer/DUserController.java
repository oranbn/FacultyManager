package DataAccessLayer;

import DataAccessLayer.DTOs.DTO;
import DataAccessLayer.DTOs.DUser;

import java.sql.*;
import java.util.List;

public class DUserController extends DalController {

    public DUserController()
    {
        super("Users");
    }
    // get all users from database
    public List<DUser> selectAllUsers(){
        return null;
    }
    // insert new user to database
    public boolean insert(DUser user)
    {
        String sql = "INSERT INTO Users(DTO.IDColumnName,DUser.EmailColumnName,DUser.PasswordColumnName,DUser.BirthdayColumnName) VALUES(?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getBirthday());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    protected DTO ConvertReaderToObject(ResultSet reader) {
        DUser result = null;
        try {
            result = new DUser(reader.getInt(1), reader.getString(2), reader.getString(3),reader.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
