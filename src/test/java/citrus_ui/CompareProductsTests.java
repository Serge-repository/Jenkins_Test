package citrus_ui;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestBasis;
import pages.TestLogger;
import steps.HomePageSteps;
import steps.ProductListPageSteps;
import utils.TestListener;

@Listeners({TestListener.class})
@Epic("Compare functionality")
@Feature("Lot of products in comparison")
public class CompareProductsTests extends TestBasis {

    HomePageSteps homePageSteps = new HomePageSteps();
    ProductListPageSteps productListPageSteps = new ProductListPageSteps();
    TestLogger testLogger = new TestLogger();

    @Severity(SeverityLevel.NORMAL)
    @Description("3 products in comparison")
    @Story("Compare page")
    @Test
    public void compareThreeProducts() {
        testLogger.log("Select Acer notebooks");
        homePageSteps.searchAcerNotebooks();
        productListPageSteps.setUpPrices("5000", "20000");

        String firstProductName = productListPageSteps.getProductNameUsingSearchFromSideMenu(0);
        String secondProductName = productListPageSteps.getProductNameUsingSearchFromSideMenu(1);
        String firstProductPrice = productListPageSteps.getProductPriceUsingSearchFromSideMenu(0);
        String secondProductPrice = productListPageSteps.getProductPriceUsingSearchFromSideMenu(1);
        productListPageSteps.closeBanner();

        testLogger.log("Add first and second laptop to comparison");
        productListPageSteps.clickOnCompareButtonIfSideSearchUsed(0);
        productListPageSteps.clickOnCompareButtonIfSideSearchUsed(1);

        testLogger.log("Open compare menu");
        productListPageSteps.clickCompareHeaderButton();

        testLogger.log("Verification of 2 products in comparison, prices, names");
        productListPageSteps.verifyNumberOfGoodsInCompare("2");
        productListPageSteps.verifyGoodInCompareName(0, secondProductName, 26);
        productListPageSteps.verifyGoodInCompareName(2, firstProductName, 26);
        productListPageSteps.verifyGoodInComparePrice(0, secondProductPrice);
        productListPageSteps.verifyGoodInComparePrice(2, firstProductPrice);

        testLogger.log("Search Acer notebook via side menu");
        productListPageSteps.openSideMenuAndSearchAcer();
        productListPageSteps.closeBanner();
        productListPageSteps.setUpPrices("5000", "20000");
        String thirdProductName = productListPageSteps.getProductNameUsingSearchFromSideMenu(2);
        String thirdProductPrice = productListPageSteps.getProductPriceUsingSearchFromSideMenu(2);
        productListPageSteps.closeBanner();

        testLogger.log("Add third laptop to comparison");
        productListPageSteps.clickOnCompareButtonIfSideSearchUsed(2);
        productListPageSteps.clickCompareHeaderButton();
        productListPageSteps.closeBanner();

        testLogger.log("Verification of 3 products in comparison, prices, names");
        productListPageSteps.verifyNumberOfGoodsInCompare("3");
        productListPageSteps.verifyGoodInCompareName(0, thirdProductName, 26);
        productListPageSteps.verifyGoodInComparePrice(2, thirdProductPrice);
    }
}
