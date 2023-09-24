package Task1;
import Task1.Data.DataProvider.DataProviderClass;
import Task1.Steps.PracticeFormPageSteps;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;
public class PracticeTest {
    PracticeFormPageSteps practiceFormPageSteps = new PracticeFormPageSteps();
    @BeforeTest
    public void start(){
        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = false;
        open("https://demoqa.com/automation-practice-form");
    }
    @Test(dataProvider = "dpMethod",dataProviderClass = DataProviderClass.class)
    public void test(Integer id ,String firstName, String lastName, String mobileNum){
        practiceFormPageSteps.
                fillFirstName(firstName).
                fillLastName(lastName).
                fillMobileNumber(mobileNum);
    }
}
