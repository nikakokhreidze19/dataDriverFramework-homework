package Task2;

import Task1.Data.ConnectionDatabase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestClass {
    Connection connection;
    SoftAssert softAssert;

    @BeforeTest
    public void start() {
        softAssert = new SoftAssert();
        connection = ConnectionDatabase.getConnection();
    }
    @Test
    public void test() throws SQLException {
        List<String> rowData = new ArrayList<>();
        rowData.add("irakli");
        rowData.add("kokhre");
        rowData.add("551324132");


        int id1 = InsertData.insertStudentInfo(connection, rowData.get(0), rowData.get(1), rowData.get(2));
        //close connect
        connection.close();
        //reconnect
        connection = ConnectionDatabase.getConnection();
        /*აქ ვაკეთებ ვალიდაციას რო ბაზიდან წამოღებული ბოლო როუს აიდი არ არის ტოლი ბაზაში
         ჩვენს მიერ ჩამატებულ როუს აიდის, ტესტში ვიყენებ მხოლოდ დინამიურად წამოღებულ აიდებს
         */
        softAssert.assertNotEquals(id1, InsertData.getLastId());
        /* აქ უკვე მეორეჯერ ვაინსერტებ ინფოს, ვუკეთებ ქომითს და ვალიდაციას რომ ბაზაში ბოლო როუს აიდი
        ტოლია ჩვენს მიერ დაინსერტებული ინფოს აიდის , გამოდის რომ დაქომითდა.
         */
        int id2 = InsertData.insertStudentInfo(connection, rowData.get(0), rowData.get(1), rowData.get(2));
        connection.commit();

        //validate id
        softAssert.assertEquals(id2, InsertData.getLastId());

        //validate info
        List<Object> actualRowData = SelectData.getData(id2);
        for (int i = 0; i < actualRowData.size(); i++) {
            softAssert.assertEquals(actualRowData.get(i), rowData.get(i));
        }
        //update
        String expectName = "saba";
        String nameData = UpdateData.updateStudentInfo(id2, expectName);
        //validate updated name
        softAssert.assertEquals(nameData, expectName);
    }
    @AfterTest
    public void tearDown() {
        softAssert.assertAll();
    }
}