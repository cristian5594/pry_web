package steps;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.*;

public class CompraSteps {
    private static Playwright playwright;
    private static Browser browser;
    private BrowserContext context;
    private Page page;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        loginPage = new LoginPage(page);
        productsPage = new ProductsPage(page);
        cartPage = new CartPage(page);
        checkoutPage = new CheckoutPage(page);
    }

    @After
    public void tearDown() {
        context.close();
        browser.close();
        playwright.close();
    }

    @Given("que el usuario navega a la página de login")
    public void que_el_usuario_navega_a_la_página_de_login() {
        loginPage.gotoLogin();
    }

    @When("ingresa el usuario {string} y la contraseña {string}")
    public void ingresa_el_usuario_y_la_contraseña(String usuario, String contraseña) {
        loginPage.login(usuario, contraseña);
    }

    @When("hace clic en el botón de login")
    public void hace_clic_en_el_boton_de_login() {
        Locator loginButton = page.locator("#login-button");
        loginButton.waitFor();
        loginButton.click();
        page.waitForTimeout(2000);
    }

    @Then("debería ver la página de productos")
    public void deberia_ver_la_pagina_de_productos() {
        Assert.assertTrue(productsPage.isAt());
    }

    @Then("debería ver un mensaje de error de acceso")
    public void deberia_ver_un_mensaje_de_error_de_acceso() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @Given("que el usuario ha iniciado sesión como {string}")
    public void que_el_usuario_ha_iniciado_sesion_como(String usuario) {
        loginPage.gotoLogin();
        loginPage.login(usuario, "secret_sauce");
        Assert.assertTrue(productsPage.isAt());
    }

    @When("agrega el producto {string} al carrito")
    public void agrega_el_producto_al_carrito(String producto) {
        productsPage.addProductToCart(producto);
    }

    @Then("el carrito debe mostrar {int} producto")
    public void el_carrito_debe_mostrar_producto(int cantidad) {
        Assert.assertEquals(cantidad, productsPage.getCartCount());
    }

    @Given("que el usuario ha iniciado sesión y ha agregado {string} al carrito")
    public void que_el_usuario_ha_iniciado_sesion_y_ha_agregado_al_carrito(String producto) {
        loginPage.gotoLogin();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(productsPage.isAt());
        productsPage.addProductToCart(producto);
    }

    @When("navega al carrito de compras")
    public void navega_al_carrito_de_compras() {
        productsPage.goToCart();

    }

    @Then("debería ver el producto {string} en el carrito")
    public void deberia_ver_el_producto_en_el_carrito(String producto) {
        Assert.assertTrue(cartPage.hasProduct(producto));

    }

    @When("navega al carrito y completa el proceso de compra con nombre {string} apellido {string} y código postal {string}")
    public void navega_al_carrito_y_completa_el_proceso_de_compra_con_nombre_apellido_y_codigo_postal(String nombre, String apellido, String postal) {

        productsPage.goToCart();
        cartPage.goToCheckout();
        checkoutPage.fillInfo(nombre, apellido, postal);
        page.waitForTimeout(3000);
        checkoutPage.finishCheckout();

    }

    @Then("debería ver la confirmación de la compra")
    public void deberia_ver_la_confirmacion_de_la_compra() {
        page.waitForTimeout(1000);
        Assert.assertTrue(checkoutPage.isConfirmed());
    }

    @When("elimina el producto {string} del carrito")
    public void elimina_el_producto_del_carrito(String producto) {
        // cartPage.removeProduct(producto);
    }

    @Then("el carrito debe estar vacío")
    public void el_carrito_debe_estar_vacio() {
        // Assert.assertTrue(cartPage.isEmpty());
    }

    @When("hace clic en Checkout")
    public void hace_clic_en_checkout() {
        // cartPage.goToCheckout();
    }

    @When("deja los campos vacíos y continúa")
    public void deja_los_campos_vacios_y_continua() {
        // checkoutPage.continueWithEmptyFields();
    }

    @Then("debería ver un mensaje de error de campos obligatorios")
    public void deberia_ver_un_mensaje_de_error_de_campos_obligatorios() {
        // Assert.assertTrue(checkoutPage.hasRequiredFieldsError());
    }
}
