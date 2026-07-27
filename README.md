# Selenium WebDriver con Java y Cucumber

Este template deja lista la infraestructura del curso y contiene dos ramas:

- `main`: es el punto de partida. No contiene el framework ni la solución de los ejercicios: los vas a construir durante las clases.
- `solution`: contiene un modelo de referencia con la estructura del framework y Page Object Model, pero sin las pruebas funcionales que desarrollaremos contra la nueva página.

## Prerrequisitos

- JDK 21 (comprobá que `JAVA_HOME` apunte a ese JDK).
- Google Chrome.
- Git.
- VS Code recomendado, con el Extension Pack for Java sugerido al abrir el proyecto.

No hace falta instalar Gradle globalmente: el repositorio incluye Gradle Wrapper 9.6.1.

## Crear y clonar tu repositorio

1. En GitHub, pulsá **Use this template** y luego **Create a new repository**.
2. Elegí el nombre y la visibilidad y seleccioná **Include all branches** para recibir tanto `main` como `solution`.
3. Copiá la URL de tu repositorio y clonalo:

```bash
git clone URL_DE_TU_REPOSITORIO
cd NOMBRE_DE_TU_REPOSITORIO
git branch --all
git switch main
```

Trabajá normalmente sobre `main`. La rama `solution` es una referencia para consultar cuando se indique durante el curso:

```bash
git switch solution
```

Para volver al punto de trabajo:

```bash
git switch main
```

Si no tenés cuenta de GitHub, podés descargar cada rama por separado mediante **Code → Download ZIP**. En ese caso no tendrás historial ni sincronización mediante Git.

## Verificar el entorno

En macOS o Linux:

```bash
java -version
./gradlew --version
./gradlew doctor
./gradlew test
```

En Windows (PowerShell o Símbolo del sistema):

```bat
java -version
gradlew.bat --version
gradlew.bat doctor
gradlew.bat test
```

`SetupCheckTest` crea `ChromeDriver` sin indicar una ruta de driver, deja que Selenium Manager lo resuelva, abre Chrome, carga contenido local, comprueba el título con TestNG y siempre cierra el navegador.

## Problemas frecuentes

- **Java no es 21:** instalá JDK 21 y volvé a abrir la terminal.
- **`JAVA_HOME` incorrecto:** hacelo apuntar a la carpeta del JDK 21 y comprobá de nuevo `java -version`.
- **`Permission denied` al usar `gradlew`:** ejecutá `chmod +x gradlew`.
- **Chrome no está instalado:** instalalo y repetí `./gradlew test`.
- **Selenium Manager no puede resolver ChromeDriver:** revisá la conexión, proxy o firewall; luego repetí el test. No descargues ni configures ChromeDriver manualmente.

## Qué está preparado y qué vas a construir

La rama `main` ya incluye Java Toolchain 21, Gradle Wrapper, Selenium, Cucumber integrado con TestNG, descubrimiento de tests y el diagnóstico `doctor`.

Durante el curso vas a crear los feature files, escenarios, step definitions, hooks, Page Objects y el resto del framework. Nada de eso viene resuelto acá.

La rama `solution` muestra cómo organizar el ciclo de vida de WebDriver, los hooks, el runner y las clases base del Page Object Model. No incluye las pruebas de la página anterior ni resuelve los nuevos ejercicios.

## Estructura de referencia de `solution`

En esta rama, el modelo queda separado por responsabilidades:

```text
src/test/java/
├── driver/DriverManager.java
├── hooks/Hooks.java
├── pages/BasePage.java
├── runner/RunCucumberTest.java
├── setup/SetupCheckTest.java
└── steps/

src/test/resources/
├── cucumber.properties
└── features/
```

- `DriverManager` crea una instancia de WebDriver por hilo y permite que los escenarios sean independientes.
- `Hooks` abre Chrome antes de cada escenario, adjunta una captura si falla y siempre cierra el navegador.
- `BasePage` recibe el driver por constructor y ofrece esperas y acciones Selenium reutilizables.
- `RunCucumberTest` integra Cucumber con TestNG y genera reportes HTML y JSON dentro de `build/reports/cucumber`.
- `SetupCheckTest` sigue siendo únicamente una comprobación técnica del entorno.

Las páginas concretas, los step definitions y los escenarios se agregarán durante el curso para la aplicación nueva.
