package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage {

    WebDriver driver;

    @FindBy(css = ".post-title")
    private WebElement headerTitle;

    public UserHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean checkIfLoggedInTitleIsThere(){
        return headerTitle.getText().equals("Logged In Successfully");
    }
}
