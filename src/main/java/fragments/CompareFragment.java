package fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.GeneralPage;

import static com.codeborne.selenide.Selenide.*;

public class CompareFragment extends GeneralPage {

    private final SelenideElement compareHeaderButton = $x("//div[@class='user-actions__compare tips-parent']");
    private final ElementsCollection buyButtons = $$x("//i[@class='icon-new-citrus-cart el-tooltip item']");
    private final SelenideElement productsInCompareNames = $x("//div[@class='products-qty']/span");

    public void clickOnCompareHeaderButton() {
        clickAction(compareHeaderButton);
        closeBanner();
    }

    public ElementsCollection getBuyButtons() {
        closeBanner();
        return getPageElements(buyButtons);
    }

    public SelenideElement goodsInCompareQuantity() {
        closeBanner();
        return productsInCompareNames;
    }
}
