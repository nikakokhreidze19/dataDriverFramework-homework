package Task1.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsData {

    public static Object[][] getInfo() throws SQLException {

        String selectStatement = "SELECT * FROM students;";

        try (
                Connection connection = ConnectionDatabase.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectStatement);
        ) {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int colCount = resultSetMetaData.getColumnCount();
            List<Object[]> dataList = new ArrayList<>();

            while (resultSet.next()) {
                Object[] rowData = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                dataList.add(rowData);
            }
            Object[][] data = new Object[dataList.size()][colCount];
            for (int i = 0; i < dataList.size(); i++) {
                data[i] = dataList.get(i);
            }

            return data;
        }
    }
}

