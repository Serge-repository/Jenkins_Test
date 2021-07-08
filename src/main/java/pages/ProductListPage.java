package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import fragments.BasketFragment;
import fragments.CompareFragment;
import fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ProductListPage extends GeneralPage {

    private final BasketFragment basketFragment = new BasketFragment();
    private final CompareFragment compareFragment = new CompareFragment();
    private final SearchFragment searchFragment = new SearchFragment();

    String phone = "(//h5[contains(text(),'%s')]/../../following-sibling::div/following-sibling::div/div/div)[2]/span";
    String buttonBuy = "(//h5[contains(text(),'%s')]/../../following-sibling::div[@class='itm-footer-desc']/ul/li/i)[1]";
    String buttonCompare = "(//h5[contains(text(),'%s')]/../../following-sibling::div[@class='itm-footer-desc']/ul/li)[2]";
    String ramSize = "//a[contains(@href,'ram-val_%s-gb')]";
    String materialType = "//a[contains(@href,'materialy-korpusa_%s')]";
    String ramSizeUncheck = "//a[contains(@href,'brand-xiaomi')][contains(text(),'%s Гб')]";
    String fullProductName = "//div[@class='product-card__name']/a[contains(@title,'%s')]";

    private final SelenideElement filterMinPrice = $x("(//div[@class='price el-input']/input)[1]");
    private final SelenideElement filterMaxPrice = $x("(//div[@class='price el-input']/input)[2]");
    private final SelenideElement expandRam = $x("(//div[@class='more-button'])[4]/span");
    private final SelenideElement rollupRam = $x("(//div[@class='more-button'])[4]/span[@class='roll-up']");
    private final SelenideElement ramBlock = $x("(//div[@class='more-button'])[3]");
    private final SelenideElement bodyMaterialBlock = $x("(//div[@class='more-button'])[7]");
    private final SelenideElement expandMaterial = $x("(//div[@class='more-button'])[8]/span");
    private final SelenideElement rollupMaterial = $x("(//div[@class='more-button'])[8]/span[@class='roll-up']");
    private final SelenideElement pageBottom = $("p.product-tags__title");
    private final ElementsCollection searchResultMaterials = $$x("//span[text()='Материалы корпуса']//following-sibling::span");
    private final ElementsCollection searchResultFull = $$x("//div[@class='product-card__preview']");
    private final ElementsCollection productNames = $$x("//div[@class='product-card__name']/a/span");
    private final ElementsCollection productPrices = $$x("//div[@class='prices__price']/span[@class='price']");
    private final SelenideElement toBasketLink = $("button.sell-description__to-basket");

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public CompareFragment getCompareFragment() {
        return compareFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public void clickOnExactProduct(String exactModelName) {
        clickAction($x(String.format(fullProductName, exactModelName)));
    }

    public String getPhonePrice(String exactModelName) {
        return $x(String.format(phone, exactModelName)).getText();
    }

    public ProductListPage buyButton(String exactModelName) {
        clickAction($x(String.format(buttonBuy, exactModelName)));
        toBasketLink.shouldBe(Condition.visible);
        clickAction(toBasketLink);
        return this;
    }

    public ProductListPage compareButton(String exactModelName) {
        clickAction($x(String.format(buttonCompare, exactModelName)));
        return this;
    }

    private void setUpPrice(SelenideElement typeOfPrice, String input) {
        typeOfPrice.clear();
        clickAction(typeOfPrice);
        typeOfPrice.val(input);
        typeOfPrice.pressEnter();
    }

    private void scrollThroughProductListPage() {
        pageBottom.scrollTo();
        ramBlock.scrollTo();
    }

    public ProductListPage setMinPrice(String input) {
        setUpPrice(filterMinPrice, input);
        closeBanner();
        return this;
    }

    public void setMaxPrice(String input) {
        closeBanner();
        setUpPrice(filterMaxPrice, input);
        scrollThroughProductListPage();
    }

    public ElementsCollection getProductsNames() {
        closeBanner();
        scrollThroughProductListPage();
        productNames.should(CollectionCondition.sizeGreaterThan(0));
        return getPageElements(productNames);
    }

    public void checkProductsMaterial(String material) {
        closeBanner();
        for (int i = 0; i < getPageElements(searchResultFull).size(); i++) {
            getPageElements(searchResultFull).get(i).scrollTo().hover();
            if (getPageElements(searchResultMaterials).get(i).getText().startsWith(material)) {
                System.out.println("Material is correct");
            }
        }
    }

    private List<Integer> getProductsPrices() {
        List<Integer> pricesList = new ArrayList<>();
        getPageElements(productPrices).forEach(s -> pricesList.add(Integer.parseInt(s.getText().replace(" ", ""))));
        return pricesList;
    }

    public void comparePricesToLimits(int minPrice, int maxPrice) throws Exception {
        for (Integer s : getProductsPrices()) {
            if (s > minPrice && s < maxPrice) {
                System.out.println("Product price " + s + " is correct");
            } else {
                throw new Exception("Product price " + s + " is incorrect");
            }
        }
    }

    private void checkIfSearchFilterOptionExpanded(SelenideElement rollup, SelenideElement expand) {
        if (!rollup.isDisplayed()) {
            clickAction(expand);
        }
    }

    private void selectFilterCheckbox(String size, SelenideElement blockToScrollTo, SelenideElement rollup, SelenideElement expand, String optionToChoose) {
        closeBanner();
        blockToScrollTo.scrollTo();
        checkIfSearchFilterOptionExpanded(rollup, expand);
        clickAction($x(String.format(optionToChoose, size)));
        pageBottom.scrollTo();
        blockToScrollTo.scrollTo();
    }

    public void selectRam(String size) {
        selectFilterCheckbox(size, ramBlock, rollupRam, expandRam, ramSize);
    }

    public void selectMaterial(String material) {
        selectFilterCheckbox(material, bodyMaterialBlock, rollupMaterial, expandMaterial, materialType);
    }

    public void unselectRam(String size) {
        closeBanner();
        checkIfSearchFilterOptionExpanded(rollupRam, expandRam);
        $x(String.format(ramSizeUncheck, size)).hover();
        clickAction($x(String.format(ramSizeUncheck, size)));
    }

    public void closeBanner() {
        super.closeBanner();
    }
}
