package DataAccessLayer;

import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DalController {
    private final String tableName;
    public DalController(String tableName)
    {
        this.tableName = tableName;
    }
    protected Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/test.db"; // database path
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    // update table row when value is string
    public boolean update(int id, String attributeName, String attributeValue)
    {
        String sql = "UPDATE "+tableName+" SET "+attributeName+" = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, attributeValue);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    // update table row when value is int
    public boolean update(int id, String attributeName, int attributeValue)
    {
        String sql = "UPDATE "+tableName+" SET "+attributeName+" = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, attributeValue);
            pstmt.setInt(2, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    protected abstract DTO ConvertReaderToObject(ResultSet reader);
    // get all objects of specific table - each row is a object
    protected List<DTO> select()
    {
        List<DTO> results = new ArrayList<>();
        String sql = "SELECT * FROM "+tableName;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                results.add(ConvertReaderToObject(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return results;
    }

}
