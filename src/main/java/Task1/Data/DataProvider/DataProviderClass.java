package Task1.Data.DataProvider;
import Task1.Data.StudentsData;
import org.testng.annotations.DataProvider;
import java.sql.SQLException;

public class DataProviderClass {
    @DataProvider(name = "dpMethod")
    public static Object[][] dpMethod() throws SQLException {
        return StudentsData.getInfo();
    }
}
