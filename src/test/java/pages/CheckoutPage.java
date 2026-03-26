package pages;

import com.microsoft.playwright.Page;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) {
        this.page = page;
    }

    public void fillInfo(String first, String last, String postal) {
        page.fill("[data-test='firstName']", first);
        page.fill("[data-test='lastName']", last);
        page.fill("[data-test='postalCode']", postal);
        page.click("[data-test='continue']");
    }

    public void finishCheckout() {
        page.click("[data-test='finish']");
    }

    public boolean isConfirmed() {
        return page.textContent(".complete-header").equals("Thank you for your order!");
    }
}
