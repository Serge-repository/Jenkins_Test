package fragments;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.GeneralPage;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class SearchFragment extends GeneralPage {

    private final SelenideElement searchField = $("#search-input");
    private final SelenideElement searchLink = $x("(//a[contains(concat(' ',normalize-space(@class),' '),'autocomplete-categories__link')])[1]");
    private final SelenideElement userIcon = $("i.icon-profil");
    private final SelenideElement sideMenuButton = $("div.menu--desktop__title");
    private final SelenideElement acerNotebooksLink = $x("//a[@href='/noutbuki-i-ultrabuki/brand-acer/']");
    private final SelenideElement mainMenuNotebooksTab = $x("(//a[@href='/planshety-noutbuki-i-kompyutery/']//child::span)[2]");
    private final ElementsCollection productNames = $$x("//div[@class='title-itm']/h5");
    private final ElementsCollection productPrices = $$("div.base-price>span");
    private final ElementsCollection productNamesViaSideSearch = $$x("//div[@class='product-card__name']/a/span");
    private final ElementsCollection productPricesViaSideSearch = $$x("//div[@class='prices__price']//child::span[@class='price']");
    private final ElementsCollection compareButtonViaSideSearch = $$("button.product-card__to-compare>span");

    public void performSearch(String input) {
        closeBanner();
        clickAction(searchField);
        searchField.val(input);
        searchLink.click();
        userIcon.hover();
    }

    public List<String> getProductsNamesList() {
        productNames.should(CollectionCondition.sizeGreaterThan(0));
        return getValuesIntoList(productNames);
    }

    public List<String> getProductsPricesList() {
        productPrices.should(CollectionCondition.sizeGreaterThan(0));
        return getValuesIntoList(productPrices);
    }

    public List<String> getProductsNamesFromSideMenu() {
        closeBanner();
        productNamesViaSideSearch.should(CollectionCondition.sizeGreaterThan(0));
        return getValuesIntoList(productNamesViaSideSearch);
    }

    public List<String> getProductsPricesFromSideMenu() {
        closeBanner();
        productPricesViaSideSearch.should(CollectionCondition.sizeGreaterThan(0));
        return getValuesIntoList(productPricesViaSideSearch);
    }

    public ElementsCollection getGoodsInCompareListPrices() {
        closeBanner();
        productPrices.should(CollectionCondition.sizeGreaterThan(0));
        return getPageElements(productPrices);
    }

    public ElementsCollection getGoodsInCompareListNames() {
        closeBanner();
        productNames.should(CollectionCondition.sizeGreaterThan(0));
        return getPageElements(productNames);
    }

    public ElementsCollection getCompareButtonsViaSideSearch() {
        closeBanner();
        compareButtonViaSideSearch.should(CollectionCondition.sizeGreaterThan(0));
        return getPageElements(compareButtonViaSideSearch);
    }

    public void openSideMenuAndSearchAcer() {
        hoverAction(sideMenuButton);
        hoverAndSearch(mainMenuNotebooksTab, acerNotebooksLink);
        closeBanner();
    }
}
