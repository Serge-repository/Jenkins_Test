package fragments;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.GeneralPage;

import static com.codeborne.selenide.Selenide.*;

public class BasketFragment extends GeneralPage {

    private final ElementsCollection numberOfGoodsInBasket = $$("div.ctrs-basket-item__products");
    private final ElementsCollection productInBasketNames = $$x("//div[@class='ctrs-basket-product']/a");
    private final ElementsCollection productInBasketPrices = $$x("//span[@class='ctrs-main-price']");
    private final SelenideElement basketCloseButton = $x("//i[@class='el-dialog__close el-icon el-icon-close']");
    private final SelenideElement basketOpenButton = $x("//div[@class='user-actions__cart h-icons__icon ctrs-basket-mini-icon']");
    private final SelenideElement basketVisibilityTrigger = $x("//span[@class='el-dialog__title']");
    private final SelenideElement basketSum = $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']");
    private final SelenideElement toBasketLink = $("button.sell-description__to-basket");

    public ElementsCollection getNumberOfGoodsInBasket() {
        numberOfGoodsInBasket.should(CollectionCondition.sizeGreaterThan(0));
        return getPageElements(numberOfGoodsInBasket);
    }

    private ElementsCollection getGoodsValuesFromBasket(ElementsCollection elementsCollection) {
        closeBanner();
        if (!basketVisibilityTrigger.isDisplayed()) {
            openBasket();
        }
        return getPageElements(elementsCollection);
    }

    public ElementsCollection getGoodsInBasketNames() {
        productInBasketNames.should(CollectionCondition.sizeGreaterThan(0));
        return getGoodsValuesFromBasket(productInBasketNames);
    }

    public ElementsCollection getGoodsInBasketPrices() {
        productInBasketPrices.should(CollectionCondition.sizeGreaterThan(0));
        return getGoodsValuesFromBasket(productInBasketPrices);
    }

    public void closeBasket() {
        clickAction(basketCloseButton);
    }

    public void openBasket() {
        clickAction(basketOpenButton);
    }

    public SelenideElement getBasketSumPrice() {
        return basketSum;
    }

    public void proceedToBasket(){
        toBasketLink.click();
    }
}
