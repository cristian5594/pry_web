Feature: Compra de productos en Sauce Demo
  Como un cliente de Sauce Demo
  Quiero poder iniciar sesión, agregar productos al carrito y completar una compra
  Para poder adquirir los productos que necesito

  Scenario: Login exitoso con usuario válido
    Given que el usuario navega a la página de login
    When ingresa el usuario "standard_user" y la contraseña "secret_sauce"
    And hace clic en el botón de login
    Then debería ver la página de productos

  Scenario: Login fallido con usuario bloqueado
    Given que el usuario navega a la página de login
    When ingresa el usuario "locked_out_user" y la contraseña "secret_sauce"
    And hace clic en el botón de login
    Then debería ver un mensaje de error de acceso

  Scenario: Agregar producto al carrito
    Given que el usuario ha iniciado sesión como "standard_user"
    When agrega el producto "Sauce Labs Backpack" al carrito
    Then el carrito debe mostrar 1 producto

  Scenario: Visualizar productos en el carrito
    Given que el usuario ha iniciado sesión y ha agregado "Sauce Labs Backpack" al carrito
    When navega al carrito de compras
    Then debería ver el producto "Sauce Labs Backpack" en el carrito

  Scenario: Completar proceso de compra
    Given que el usuario ha iniciado sesión y ha agregado "Sauce Labs Backpack" al carrito
    When navega al carrito y completa el proceso de compra con nombre "QA" apellido "Test" y código postal "12345"
    Then debería ver la confirmación de la compra

  Scenario: Eliminar producto del carrito
    Given que el usuario ha iniciado sesión y ha agregado "Sauce Labs Backpack" al carrito
    When navega al carrito de compras
    And elimina el producto "Sauce Labs Backpack" del carrito
    Then el carrito debe estar vacío

  Scenario: Checkout con campos vacíos
    Given que el usuario ha iniciado sesión y ha agregado "Sauce Labs Backpack" al carrito
    When navega al carrito de compras
    And hace clic en Checkout
    And deja los campos vacíos y continúa
    Then debería ver un mensaje de error de campos obligatorios
