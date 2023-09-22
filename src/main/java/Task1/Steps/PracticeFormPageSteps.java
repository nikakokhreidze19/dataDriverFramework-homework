package Task1.Steps;
import Task1.Pages.PracticeFormPage;
import io.qameta.allure.Step;

public class PracticeFormPageSteps {
    PracticeFormPage practiceFormPage = new PracticeFormPage();
    @Step
    public PracticeFormPageSteps fillFirstName(String firstName){
        practiceFormPage.firstNameInput.setValue(firstName);
        return this;
    }
    @Step
    public PracticeFormPageSteps fillLastName(String lastName){
        practiceFormPage.lastNameInput.setValue(lastName);
        return this;
    }
    @Step
    public PracticeFormPageSteps fillMobileNumber(String phoneNumber){
        practiceFormPage.mobileInput.setValue(phoneNumber);
        return this;
    }
}
