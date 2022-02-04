package DataAccessLayer;

import DataAccessLayer.DTOs.DTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return false;
    }
    // update table row when value is int
    public boolean update(int id, String attributeName, int attributeValue)
    {
        return false;
    }
    // get all objects of specific table - each row is a object
    protected List<DTO> select()
    {
        return null;
    }
}
