package Task2;

import Task1.Data.ConnectionDatabase;

import java.sql.*;

public class UpdateData {
    public static String updateStudentInfo(int id, String name) {
        ResultSet rs;
        String sqlUpdate =
                "UPDATE students.dbo.students " +
                        "SET firstName = ? " +
                        "WHERE id = ?";


        String selectQuery = "select * from students.dbo.students where id=" + id;
        String updatedName = "";

        try (Connection conn = ConnectionDatabase.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sqlUpdate);
             Statement statement = conn.createStatement()) {

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

            rs = statement.executeQuery(selectQuery);
            while (rs.next()) {
                updatedName = rs.getString(2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedName;
    }
}
