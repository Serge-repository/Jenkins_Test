package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;

public class GeneralPage {

    private final SelenideElement bannerButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");

    protected void hoverAction(SelenideElement selenideElement) {
        selenideElement.hover();
    }

    protected void clickAction(SelenideElement selenideElement) {
        selenideElement.click();
    }

    protected ElementsCollection getPageElements(ElementsCollection elementsCollection) {
        return elementsCollection;
    }

    protected void closeBanner() {
        new WebDriverWait(WebDriverRunner.getWebDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        if (bannerButton.isDisplayed()) {
            bannerButton.click();
        }
    }

    protected List<String> getValuesIntoList(ElementsCollection elementsCollection) {
        List<String> valuesList = new ArrayList<>();
        getPageElements(elementsCollection).forEach(s -> valuesList.add(s.getText()));
        return valuesList;
    }

    protected void hoverAndSearch(SelenideElement elementToHover, SelenideElement elementToSearch) {
        hoverAction(elementToHover);
        clickAction(elementToSearch);
    }
}
