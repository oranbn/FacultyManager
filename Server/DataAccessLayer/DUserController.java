package DataAccessLayer;

import DataAccessLayer.DTOs.DTO;
import DataAccessLayer.DTOs.DUser;

import java.sql.*;
import java.util.List;

public class DUserController extends DalController {

    public DUserController()
    {
        super("User");
    }
    // get all users from database
    public List<DUser> selectAllUsers(){
        return (List<DUser>)(List<?>)select();
    }
    // insert new user to database
    public boolean insert(DUser user)
    {
        String sql = "INSERT INTO User("+DTO.IDColumnName+","+DUser.EmailColumnName+","+DUser.PasswordColumnName+"," +DUser.FirstNameColumnName+","+DUser.LastNameColumnName+","+ DUser.IDNumberColumnName+","+DUser.PhoneNumberColumnName+","+ DUser.BirthdayColumnName+","+DUser.PermissionLevelColumnName+","+ DUser.IsEmailApprovedColumnName+","+DUser.ActivationCodeColumnName+","+DUser.ForgotPasswordColumnName+") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setString(6, user.getIdNumber());
            pstmt.setString(7, user.getPhoneNumber());
            pstmt.setString(8, user.getBirthday());
            pstmt.setInt(9, user.getPermissionLevel());
            pstmt.setBoolean(10, user.isEmailApproved());
            pstmt.setInt(11, user.getActivationCode());
            pstmt.setInt(12, user.getForgotPassword());
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
            result = new DUser(reader.getInt(1), reader.getString(2), reader.getString(3),reader.getString(4),reader.getString(5), reader.getString(6),reader.getString(7),reader.getString(8), reader.getInt(9),reader.getBoolean(10), reader.getInt(11), reader.getInt(12));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
