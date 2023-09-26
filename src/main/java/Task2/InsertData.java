package Task2;
import Task1.Data.ConnectionDatabase;
import java.sql.*;

public class InsertData {
    public static int insertStudentInfo(Connection connection, String firstName, String lastName, String phoneNumber) throws SQLException {
        ResultSet rs = null;
        int stundetId = 0;
        int newId = getLastId() + 1;

        String insertInfo = "INSERT INTO students.dbo.students(id,firstName,lastName,phone) "
                + "VALUES(?,?,?,?)";

        String selectStatement = "select id from students.dbo.students where id=" + newId;


        try (
                PreparedStatement pstmt = connection.prepareStatement(insertInfo, Statement.RETURN_GENERATED_KEYS);
                Statement statement = connection.createStatement()
        ) {
            connection.setAutoCommit(false);
            pstmt.setInt(1, newId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, phoneNumber);

            pstmt.execute();
            rs = statement.executeQuery(selectStatement);
            while (rs.next()) {
                stundetId = rs.getInt(1);
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return stundetId;
    }

    //moaqvs bazashi bolo id
    public static int getLastId() {
        String query = "SELECT TOP 1 id FROM students.dbo.students ORDER BY id DESC";
        try (Connection connection = ConnectionDatabase.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            int lastId = 0;
            if (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
            return lastId;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
