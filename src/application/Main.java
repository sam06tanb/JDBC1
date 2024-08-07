package application;
import application.data.ConnectionJDBC;
import db.DB;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        ConnectionJDBC connectionJDBC = new ConnectionJDBC();

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
           //Selects the table and starts connection
        try {
            String query = "SELECT * FROM department";
            conn = connectionJDBC.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);

            // Walks through all the department column
            while (rs.next()) {
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
            //close all connections
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}