package pages.enums;

public enum Phones {
    SAMSUNG_PHONES("//a[@href='/smartfony/brand-samsung/']"),
    XIAOMI_PHONES("//a[@href='/smartfony/brand-xiaomi/']"),
    APPLE_PHONES("//a[@href='/smartfony/brand-apple/']"),

    IPHONE_SE("(//b[@class='buy-section__new-price'])[1]");

    private final String exactPhoneLocator;

    Phones(String exactPhoneLocator) {
        this.exactPhoneLocator = exactPhoneLocator;
    }

    public String getExactPhoneLocator() {
        return exactPhoneLocator;
    }
}
