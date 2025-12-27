package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected WebDriver driver;
    protected Properties properties;

    @BeforeClass
    public void setUp() throws IOException {
        driver = DriverManager.getInstance().getDriver();

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/test.properties");
        properties = new Properties();
        properties.load(fileInputStream);
    }

    @AfterClass
    public void close(){
        DriverManager.quitDriver();
    }
}
