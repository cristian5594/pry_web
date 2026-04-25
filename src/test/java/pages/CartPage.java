package pages;

import com.microsoft.playwright.Page;

public class CartPage {
    private final Page page;

    public CartPage(Page page) {
        this.page = page;
    }

    public boolean hasProduct(String productName) {
        page.waitForTimeout(2000);
        return page.locator(".cart_item:has-text('" + productName + "')").count() > 0;

    }

    public void goToCheckout() {
        page.click("[data-test='checkout']");
    }
}
