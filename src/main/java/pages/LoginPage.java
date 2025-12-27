package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage> {

    WebDriver driver;
    String url;

    @FindBy(css = "input#username")
    private WebElement userNameField;
    @FindBy(css = "input#password")
    private WebElement userPasswordField;
    @FindBy(css = "button#submit")
    private WebElement submitButton;
    @FindBy(css = "div#error")
    private WebElement errorToaster;

    public LoginPage(WebDriver driver,String url){
        this.driver = driver;
        this.url = url;
        PageFactory.initElements(driver,this);
    }

    @Override
    protected void load(){
        System.out.println("Page not loaded. Navigating to: " + url);
        driver.get(url);
    }

    @Override
    protected void isLoaded(){
        try{
            Assert.assertTrue(userNameField.isDisplayed(),"Username field not visible");
        }catch (Exception ex){
            throw new Error("Login Page is not loaded yet!");
        }
    }

    public LoginPage enterUserName(String userName){
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage enterUserPassword(String password){
        userPasswordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSubmitButton(){
        submitButton.click();
        return this;
    }

    public boolean checkIfErrorToasterDisplayed(){
        return errorToaster.getText().equals("Your username is invalid!");
    }
}
