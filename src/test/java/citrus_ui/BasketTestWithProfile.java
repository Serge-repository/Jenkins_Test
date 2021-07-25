package citrus_ui;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.TestBasisWithProfile;
import steps.HomePageSteps;
import steps.ProductListPageSteps;
import steps.ProductPageSteps;

import static pages.enums.Phones.APPLE_PHONES;
import static pages.enums.Phones.IPHONE_SE;

//@Listeners({TestListener.class})
@Epic("Basket functionality")
@Feature("Add to basket scenarios")
public class BasketTestWithProfile extends TestBasisWithProfile {

    HomePageSteps homePageSteps = new HomePageSteps();
    ProductListPageSteps productListPageSteps = new ProductListPageSteps();
    ProductPageSteps productPageSteps = new ProductPageSteps();

    @Severity(SeverityLevel.NORMAL)
    @Description("Description 111")
    @Story("Product page")
    @Test
    public void addProductToBasketUsingProductPage() {
        homePageSteps.searchPhones(APPLE_PHONES);
        productListPageSteps.selectExactProduct("Apple iPhone SE 2020 64Gb PRODUCT Red");
        String iPhoneSEPrice = productPageSteps.getProductPrice(IPHONE_SE);
        productPageSteps.clickBuyButton();
        productPageSteps.proceedToBasket();
        productPageSteps.verifyQuantityNamePriceForOneProduct("Apple iPhone SE 2020 64Gb", iPhoneSEPrice);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Description 222")
    @Story("Search")
    @Test
    public void addToBasketUsingSearch() {
        homePageSteps.searchProductFromSearchBar("Apple iPhone 11");
        String iPhone11Price = productListPageSteps.getProductPriceByName("Apple iPhone 11 128Gb Black");
        productListPageSteps.clickBuyButton("Apple iPhone 11 128Gb Black");
        productPageSteps.verifyQuantityNamePriceForOneProduct("Apple iPhone 11 128Gb Black", iPhone11Price);
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Description 333")
    @Story("Two products")
    @Test
    public void addTwoProductsToBasket() {
        homePageSteps.searchProductFromSearchBar("Apple iPhone");
        String firstProductName = productListPageSteps.getProductName(0);
        String secondProductName = productListPageSteps.getProductName(1);
        String firstProductPrice = productListPageSteps.getProductPriceByPositionInResultsList(0);
        String secondProductPrice = productListPageSteps.getProductPriceByPositionInResultsList(1);
        int priceSumAsInt = Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice);
        String priceSum = String.valueOf(priceSumAsInt);

        productListPageSteps.clickBuyButton(firstProductName);
        productListPageSteps.closeBasket();
        productListPageSteps.clickBuyButton(secondProductName);
        productListPageSteps.verifyNumberOfGoodsInBasket(2);
        productListPageSteps.verifyGoodInBasketName(0, firstProductName);
        productListPageSteps.verifyGoodInBasketName(1, secondProductName);
        productListPageSteps.verifyGoodInBasketPrice(0, firstProductPrice, 1);
        productListPageSteps.verifyGoodInBasketPrice(1, secondProductPrice, 1);
        productListPageSteps.verifyBasketSumPrice(priceSum);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Description 444")
    @Story("Comparison")
    @Test
    public void addTwoProductsToBasketFromComparison() {
        homePageSteps.searchProductFromSearchBar("Apple iPhone");
        String firstProductName = productListPageSteps.getProductName(0);
        String secondProductName = productListPageSteps.getProductName(1);
        String firstProductPrice = productListPageSteps.getProductPriceByPositionInResultsList(0);
        String secondProductPrice = productListPageSteps.getProductPriceByPositionInResultsList(1);
        int priceSumAsInt = Integer.parseInt(firstProductPrice) + Integer.parseInt(secondProductPrice);
        String priceSum = String.valueOf(priceSumAsInt);

        productListPageSteps.clickCompareButton(firstProductName);
        productListPageSteps.clickCompareButton(secondProductName);
        productListPageSteps.clickCompareHeaderButton();
        productListPageSteps.clickBuyButtonFromComparePage(0);
        productListPageSteps.closeBasket();
        productListPageSteps.clickBuyButtonFromComparePage(2);
        productListPageSteps.proceedToBasket();
        productListPageSteps.verifyNumberOfGoodsInBasket(2);
        productListPageSteps.verifyGoodInBasketName(0, firstProductName);
        productListPageSteps.verifyGoodInBasketName(1, secondProductName);
        productListPageSteps.verifyGoodInBasketPrice(0, firstProductPrice, 2);
        productListPageSteps.verifyGoodInBasketPrice(1, secondProductPrice, 2);
        productListPageSteps.verifyBasketSumPrice(priceSum);
    }
}
