package Task2;

import Task1.Data.ConnectionDatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelectData {
    public static List<Object> getData(int id) {

        List<Object> ListOfData = new ArrayList<>();
        String selectQuery = "select * from students.dbo.students where id=" + id;

        try (Connection conn = ConnectionDatabase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectQuery)) {
            while (rs.next()) {
                ListOfData.add(rs.getString(2));
                ListOfData.add(rs.getString(3));
                ListOfData.add(rs.getString(4));
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListOfData;
    }

}
