
# Demo QA Automation

## Descripción
Este proyecto implementa una suite de pruebas automatizadas para la aplicación web Sauce Demo utilizando Playwright y Cucumber en Java. Se emplea el patrón de diseño Page Object Model para mantener el código organizado y escalable.

## Requisitos
- Java 11
- Maven 3.6+
- Node.js (para instalar Playwright)
- Google Chrome o Microsoft Edge instalado

## Instalación
1. Clonar el repositorio:
   git clone <URL_DEL_REPOSITORIO>
2. Instalar dependencias de Playwright:
   npx playwright install
3. Instalar dependencias de Maven:
   mvn clean install

## Estructura del Proyecto
- `src/test/java/features/`: Archivos feature en Gherkin con los escenarios de prueba.
- `src/test/java/pages/`: Clases Page Object Model para cada página de la aplicación.
- `src/test/java/steps/`: Definiciones de pasos de Cucumber.
- `src/test/java/support/`: Runner de Cucumber y utilidades.

## Ejecución de Pruebas
Para ejecutar todos los tests y generar el reporte HTML:

	 mvn clean test
     -Ubicado: src/test/java/support
     Ejecutar con Tag unico en RunCucumberTest   
El reporte se genera en `target/cucumber-report.html`.

## Escenarios Cubiertos
- Inicio de sesión con credenciales válidas (standard_user)
- Inicio de sesión con credenciales inválidas
- Inicio de sesión con usuario bloqueado (locked_out_user)
- Agregar productos al carrito
- Visualizar productos en el carrito
- Completar el proceso de compra
- Remover productos del carrito
- Intentar comprar con el carrito vacío

## Diseño y Buenas Prácticas
- Se utiliza el patrón Page Object Model para separar la lógica de interacción con la UI.
- Los escenarios están escritos en Gherkin siguiendo criterios de aceptación claros.

## Personalización
Para agregar nuevos escenarios:
1. Crear o editar archivos `.feature` en `src/test/java/features/`.
2. Implementar los nuevos pasos en las clases de `src/test/java/steps/`.
3. Añadir métodos necesarios en las clases de página en `src/test/java/pages/`.

## Notas
- Asegúrese de que la codificación de archivos sea UTF-8 para evitar problemas de reconocimiento de pasos.
- Asegúrese de installar el maven y el jdk correspondientes 
