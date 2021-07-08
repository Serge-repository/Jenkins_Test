package citrus_ui;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.TestBasis;
import steps.HomePageSteps;
import steps.ProductListPageSteps;
import utils.TestListener;

import static pages.enums.Phones.SAMSUNG_PHONES;
import static pages.enums.Phones.XIAOMI_PHONES;

@Listeners({TestListener.class})
@Epic("Filter functionality")
@Feature("Test different types of filters")
public class FilterTests extends TestBasis {
    HomePageSteps homePageSteps = new HomePageSteps();
    ProductListPageSteps productListPageSteps = new ProductListPageSteps();

    @Severity(SeverityLevel.BLOCKER)
    @Description("Test price filter")
    @Story("Compare functions")
    @Test
    public void priceFilter() throws Exception {
        homePageSteps.searchPhones(SAMSUNG_PHONES);
        productListPageSteps.setUpPrices("5000", "10000");
        productListPageSteps.closeBanner();
        productListPageSteps.verifyProductListParameters("Samsung");
        productListPageSteps.verifyProductListPrices(5000, 10000);
    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Test memory size filter")
    @Story("Compare functions")
    @Test
    public void memorySizeFilter() {
        homePageSteps.searchPhones(XIAOMI_PHONES);
        productListPageSteps.selectRamSize("6");
        productListPageSteps.verifyProductListParameters("Xiaomi");
        productListPageSteps.verifyProductListParameters("6/");
        productListPageSteps.unselectRamSize("6");
        productListPageSteps.selectRamSize("8");
        productListPageSteps.verifyProductListParameters("Xiaomi");
        productListPageSteps.verifyProductListParameters("8/");
    }

    @Severity(SeverityLevel.TRIVIAL)
    @Description("Test material filter")
    @Story("Compare functions")
    @Test
    public void bodyMaterialFilter() {
        homePageSteps.searchPhones(XIAOMI_PHONES);
        productListPageSteps.selectProductMaterial("metall");
        productListPageSteps.verifyProductListParameters("Xiaomi");
        productListPageSteps.closeBanner();
        productListPageSteps.verifyMaterial("Металл");
    }
}
