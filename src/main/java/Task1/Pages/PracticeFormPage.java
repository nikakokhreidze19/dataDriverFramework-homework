package Task1.Pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

public class PracticeFormPage {
    public SelenideElement
            firstNameInput = $(byId("firstName")),
            lastNameInput = $(byId("lastName")),
            mobileInput = $(byId("userNumber"));
}
