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
        page.click("[id='checkout']");
    }

    public void getContinue(){
        page.click("[id='continue']");
    }

    public void removeProducto(String producto) {
        page.locator(".cart_item:has-text('" + producto + "') [data-test^='remove']").click();
    }

    public boolean getVacio() {
        page.waitForTimeout(2000);
        return page.locator(".cart_item").count() == 0;
    }

    public boolean getValidarMensaje() {
        String mensaje = page.locator("[data-test='error']").textContent();
        return mensaje.equals("Error: First Name is required");
    }
}
