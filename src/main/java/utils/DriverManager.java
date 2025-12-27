package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class DriverManager {

    private static ThreadLocal<DriverManager> instance = new ThreadLocal<>();

    private WebDriver driver;

    private DriverManager(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public static DriverManager getInstance(){
        if(instance.get() == null)
            instance.set(new DriverManager());
        return instance.get();
    }

    public WebDriver getDriver(){
        return driver;
    }

    public static void quitDriver(){
        if(instance.get() != null){
            instance.get().driver.quit();
            instance.remove();
        }
    }
}
