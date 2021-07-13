package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static com.codeborne.selenide.Selenide.open;

public class TestBasis {

    @BeforeClass
    public void configsSetup(){
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        Configuration.timeout = 6000;
    }

    @Parameters({"env"})
    @BeforeMethod
    public void beforeMethod(final String env){
        open(env);
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        waitForPageToBeFullyLoaded();
    }

    private void waitForPageToBeFullyLoaded(){
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebDriver getDriver() {
        return WebDriverRunner.getWebDriver();
    }
}