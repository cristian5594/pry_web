package pages;

import com.microsoft.playwright.Page;

public class ProductsPage {
    private final Page page;

    public ProductsPage(Page page) {
        this.page = page;
    }

    public boolean isAt() {
        return page.textContent(".title").equals("Products");
    }

    public void addProductToCart(String productName) {
        // Convierte el nombre del producto a formato de data-test
        String id = productName.toLowerCase().replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
        String selector = String.format("button[data-test='add-to-cart-%s']", id);
        page.waitForSelector(selector);
        page.click(selector);
    }

    public int getCartCount() {
        String count = page.textContent(".shopping_cart_badge");
        return Integer.parseInt(count);
    }

    public void goToCart() {
        page.click(".shopping_cart_link");
    }
}
