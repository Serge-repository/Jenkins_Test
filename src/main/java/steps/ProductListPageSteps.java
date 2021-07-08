package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.ProductListPage;

public class ProductListPageSteps {
    ProductListPage productListPage = new ProductListPage();

    @Step("Set up minimum and maximum prices")
    public void setUpPrices(String minPrice, String maxPrice) {
        productListPage.setMinPrice(minPrice)
                .setMaxPrice(maxPrice);
    }

    @Step("Verify required parameter on product list page")
    public void verifyProductListParameters(String parameter) {
        productListPage.getProductsNames().forEach(s -> s.shouldHave(Condition.text(parameter)));
    }

    @Step("Verify that prices were applied")
    public void verifyProductListPrices(int minPriceToCompare, int maxPriceToCompare) throws Exception {
        productListPage.comparePricesToLimits(minPriceToCompare, maxPriceToCompare);
    }

    @Step("Select RAM size")
    public void selectRamSize(String size) {
        productListPage.selectRam(size);
    }

    @Step("Unselect RAM size")
    public void unselectRamSize(String size) {
        productListPage.unselectRam(size);
    }

    @Step("Select product material")
    public void selectProductMaterial(String material) {
        productListPage.selectMaterial(material);
    }

    @Step("Close banner")
    public void closeBanner() {
        productListPage.closeBanner();
    }

    @Step("Verify that material filter was applied")
    public void verifyMaterial(String material) {
        productListPage.checkProductsMaterial(material);
    }

    @Step("Verify that material filter was applied")
    public void selectExactProduct(String exactModelName) {
        productListPage.clickOnExactProduct(exactModelName);
    }

    @Step("Remember product price")
    public String getProductPriceByName(String exactModelName) {
        return productListPage.getPhonePrice(exactModelName);
    }

    @Step("Remember product price")
    public String getProductPriceByPositionInResultsList(int numberOfProductFromSearchResult) {
        return productListPage.getSearchFragment().getProductsPricesList().get(numberOfProductFromSearchResult).replace(" ", "");
    }

    @Step("Click on buy button")
    public void clickBuyButton(String exactProductName) {
        productListPage.buyButton(exactProductName);
    }

    @Step("Remember product name")
    public String getProductName(int numberOfProductFromSearchResult) {
        return productListPage.getSearchFragment().getProductsNamesList().get(numberOfProductFromSearchResult);
    }

    @Step("Close basket")
    public void closeBasket() {
        productListPage.getBasketFragment().closeBasket();
    }

    @Step("Verify that number of goods in basket is correct")
    public void verifyNumberOfGoodsInBasket(int expectedSizeOfBasket) {
        productListPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(expectedSizeOfBasket);
    }

    @Step("Verify that number of goods in compare list is equal to expected")
    public void verifyNumberOfGoodsInCompare(String expectedSizeOfBasket) {
        productListPage.getCompareFragment().goodsInCompareQuantity().shouldHave(Condition.text(expectedSizeOfBasket));
        closeBanner();
    }

    @Step("Verify that good in basket name is correct")
    public void verifyGoodInBasketName(int positionInBasket, String productName) {
        productListPage.getBasketFragment().getGoodsInBasketNames().get(positionInBasket).shouldHave(Condition.text(productName));
    }

    @Step("Verify that good in basket price is correct")
    public void verifyGoodInBasketPrice(int positionInBasket, String productPrice, int substringParameter) {
        productListPage.getBasketFragment().getGoodsInBasketPrices().get(positionInBasket)
                .shouldHave(Condition.text(productPrice.substring(0, substringParameter)));
    }

    @Step("Verify that sum price of goods in basket is correct")
    public void verifyBasketSumPrice(String expectedPriceSum) {
        productListPage.getBasketFragment().getBasketSumPrice().shouldHave(Condition.text(expectedPriceSum.substring(0, 1)));
    }

    @Step("Click on goods` compare button from the list")
    public void clickCompareButton(String productName) {
        productListPage.compareButton(productName);
    }

    @Step("Click on compare header button")
    public void clickCompareHeaderButton() {
        productListPage.getCompareFragment().clickOnCompareHeaderButton();
    }

    @Step("Click on goods` buy button from compare page")
    public void clickBuyButtonFromComparePage(int positionOnComparePage) {
        productListPage.getCompareFragment().getBuyButtons().get(positionOnComparePage).click();
    }

    @Step("Perform search from side menu and get product name")
    public String getProductNameUsingSearchFromSideMenu(int productPosition) {
        return productListPage.getSearchFragment().getProductsNamesFromSideMenu().get(productPosition);
    }

    @Step("Perform search from side menu and get product price")
    public String getProductPriceUsingSearchFromSideMenu(int productPosition) {
        return productListPage.getSearchFragment().getProductsPricesFromSideMenu().get(productPosition);
    }

    @Step("Click on compare button using side search")
    public void clickOnCompareButtonIfSideSearchUsed(int productPosition) {
        productListPage.getSearchFragment().getCompareButtonsViaSideSearch().get(productPosition).hover().hover().click();
        closeBanner();
    }

    @Step("Verify that good in compare list name is correct")
    public void verifyGoodInCompareName(int productPositionInTheList, String productName, int quantityOfSymbolsToSubstring) {
        productListPage.getSearchFragment().getGoodsInCompareListNames().get(productPositionInTheList)
                .shouldHave(Condition.text(productName.substring(0, quantityOfSymbolsToSubstring)));
    }

    @Step("Verify that good in compare list price is correct")
    public void verifyGoodInComparePrice(int productPositionInTheList, String productName) {
        productListPage.getSearchFragment().getGoodsInCompareListPrices().get(productPositionInTheList)
                .shouldHave(Condition.text(productName));
    }

    @Step("Open side menu and search Acer notebooks")
    public void openSideMenuAndSearchAcer() {
        productListPage.getSearchFragment().openSideMenuAndSearchAcer();
    }

    @Step("Open the basket")
    public void proceedToBasket(){
        productListPage.getBasketFragment().proceedToBasket();
    }
}
