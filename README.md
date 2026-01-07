# Java Selenium Loadable Component Pattern

This repository demonstrates the implementation of the **LoadableComponent Pattern** in a Selenium Automation Framework. This pattern utilizes Selenium's native `LoadableComponent` class to standardize how pages are loaded and verified.



## 🎯 What is the Loadable Component Pattern?

The **LoadableComponent** is a base class provided by the Selenium library (`org.openqa.selenium.support.ui.LoadableComponent`). It forces Page Objects to define two critical pieces of logic:

1.  **How to load the page** (e.g., navigating to a URL).
2.  **How to check if the page is loaded** (e.g., verifying a specific element or title exists).

If the check fails, the framework automatically tries to load the page and checks again. If it still fails, it throws an Error, failing the test immediately.

### Key Methods
* `load()`: Contains the logic to navigate to the page.
* `isLoaded()`: Contains assertions to verify the page is ready.
* `get()`: The magic method that calls `isLoaded()`. If it fails, it calls `load()`, then `isLoaded()` again.

## 🚀 Key Benefits

* **Fail-Fast Mechanism**: If a page doesn't load, the test fails immediately at the Page Object level, not later when trying to find an element.
* **Reduced Flakiness**: Enforces a strict check that the page is ready before any test steps execute.
* **Standardization**: Every page follows the exact same structure for navigation and verification.

## 🛠️ Tech Stack

* **Language**: Java (JDK 8+)
* **Automation**: Selenium WebDriver
* **Test Runner**: TestNG
* **Build Tool**: Maven

## 📂 Project Structure

```text
JavaSeleniumLoadableComponentPattern
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── pages
│   │               └── BasePage.java            # Abstract class (optional)
│   │               └── LoginPage.java           # Extends LoadableComponent<LoginPage>
│   ├── test
│   │   └── java
│   │       └── com
│   │           └── tests
│   │               └── LoginTest.java           # Uses .get() to instantiate pages
├── pom.xml                                      # Dependencies
└── testng.xml                                   # Suite Configuration
⚙️ Implementation Example
1. The Page Object (LoginPage.java)
Your page classes extend LoadableComponent<T> and implement the required methods.
```

Java

public class LoginPage extends LoadableComponent<LoginPage> {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // We do NOT call initElements here or navigation logic usually
    }

    @Override
    protected void load() {
        // Logic to navigate to the page if it's not already loaded
        driver.get("[https://example.com/login](https://example.com/login)");
    }

    @Override
    protected void isLoaded() throws Error {
        // Check if we are on the right page
        String url = driver.getCurrentUrl();
        if (!url.contains("login")) {
            throw new Error("Login Page was not loaded!");
        }
        
        // Alternatively, check for a specific element
        try {
            driver.findElement(By.id("username"));
        } catch (NoSuchElementException e) {
            throw new Error("Username field not present: Page not loaded");
        }
    }
    
    public void login(String user, String pass) {
        driver.findElement(By.id("username")).sendKeys(user);
        // ... rest of logic
    }
}
2. The Test Class
In your test, you simply call .get() to ensure the page is ready.

Java

@Test
public void testLogin() {
    // .get() triggers the load()/isLoaded() cycle automatically
    LoginPage loginPage = new LoginPage(driver).get();
    
    loginPage.login("admin", "password");
}
📦 Dependencies
Ensure your pom.xml includes:

selenium-java

testng

webdrivermanager

🏃‍♂️ How to Run
Using Maven
Run the tests via the command line:

Bash

mvn clean test
Using IDE (IntelliJ / Eclipse)
Open testng.xml or the specific Test class.

Right-click and select Run.

🤝 Contributing
Fork the repository.

Create your feature branch.

Implement a new Page Object using LoadableComponent.

Submit a Pull Request.

📚 References
Selenium API Docs: LoadableComponent

BrowserStack: Loadable Components in Selenium
