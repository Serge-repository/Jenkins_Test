package pages;

import com.codeborne.selenide.SelenideElement;
import fragments.BasketFragment;
import pages.enums.Phones;

import static com.codeborne.selenide.Selenide.$x;

public class ProductPage extends GeneralPage {

    private final BasketFragment basketFragment = new BasketFragment();

    private final SelenideElement buyButton = $x("(//div[@class='buy-section__main-buy']/button)[1]");

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public String getProductPrice(Phones phone){
        SelenideElement element = $x(phone.getExactPhoneLocator());
        String result = element.getText().replaceAll("₴", "").trim();
        return result;
    }

    public void clickOnBuyButton(){
        clickAction(buyButton);
    }
}
