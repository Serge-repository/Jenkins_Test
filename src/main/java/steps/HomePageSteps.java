package steps;

import io.qameta.allure.Step;
import pages.HomePage;
import pages.enums.Phones;

public class HomePageSteps {
    HomePage homePage = new HomePage();

    @Step("Search phones by manufacturer")
    public void searchPhones(Phones phone) {
        homePage.searchPhoneByModel(phone);
    }

    @Step("Search product using search bar in page header")
    public void searchProductFromSearchBar(String exactProductName) {
        homePage.getSearchFragment().performSearch(exactProductName);
    }

    @Step("Search Acer notebooks")
    public void searchAcerNotebooks() {
        homePage.searchAcerNotebooks();
    }
}
