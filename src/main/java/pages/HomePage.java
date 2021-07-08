package pages;

import com.codeborne.selenide.SelenideElement;
import fragments.SearchFragment;
import pages.enums.Phones;

import static com.codeborne.selenide.Selenide.$x;

public class HomePage extends GeneralPage {

    private final SearchFragment searchFragment = new SearchFragment();

    private final SelenideElement mainMenuSmartphonesTab = $x("(//a[@href='/smartfony/']//child::span)[3]");
    private final SelenideElement mainMenuNotebooksTab = $x("(//a[@href='/planshety-noutbuki-i-kompyutery/']//child::span)[3]");
    private final SelenideElement acerNotebooksLink = $x("//a[@href='/noutbuki-i-ultrabuki/brand-acer/']");

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public void searchAcerNotebooks() {
        hoverAndSearch(mainMenuNotebooksTab, acerNotebooksLink);
    }

    public void searchPhoneByModel (Phones phone) {
        hoverAndSearch(mainMenuSmartphonesTab, $x(phone.getExactPhoneLocator()));
    }
}
