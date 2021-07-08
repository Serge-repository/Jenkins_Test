package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import pages.ProductPage;
import pages.enums.Phones;

public class ProductPageSteps {
    ProductPage productPage = new ProductPage();

//    private final SelenideElement toBasketLink = $("button.sell-description__to-basket");

    @Step("Get required product price")
    public String getProductPrice(Phones phone) {
        return productPage.getProductPrice(phone);
    }

    @Step("Click buy button on products` page")
    public void clickBuyButton() {
        productPage.clickOnBuyButton();
    }

    @Step("Single product name, price and quantity verification in basket")
    public void verifyQuantityNamePriceForOneProduct(String name, String price) {
        productPage.getBasketFragment().getNumberOfGoodsInBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getGoodsInBasketNames().get(0).shouldHave(Condition.text(name));
        productPage.getBasketFragment().getGoodsInBasketPrices().get(0).shouldHave(Condition.text(price));
    }

    @Step("Open the basket")
    public void proceedToBasket(){
        productPage.getBasketFragment().proceedToBasket();
    }

}
