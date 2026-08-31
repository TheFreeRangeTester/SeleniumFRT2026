# Selenium WebDriver con Java y Cucumber

Proyecto del curso para aprender automatización web con Selenium, Java, Cucumber y TestNG.

El repositorio contiene dos ramas:

- `main`: punto de partida para construir el framework durante las clases.
- `solution`: implementación de referencia con features, steps, hooks y Page Objects.

## Requisitos

Antes de empezar, instalá:

- JDK 21.
- Google Chrome.
- Git.
- VS Code (recomendado) y el Extension Pack for Java sugerido por el proyecto.

No instales Gradle globalmente. El proyecto incluye Gradle Wrapper 9.6.1 para que todas las personas usen la misma versión.

## Usuario de prueba

Las credenciales compartidas por los escenarios de `solution` se reciben mediante estas variables de entorno:

```text
TEST_USER_EMAIL
TEST_USER_PASSWORD
```

Las features identifican al `usuario de prueba` y los steps leen esas variables mediante `TestUser`. No copies credenciales dentro del repositorio, los archivos `.feature`, los comandos compartidos ni los reportes.

Antes de ejecutar la suite localmente, definilas en tu terminal. Por ejemplo, en macOS o Linux:

```bash
export TEST_USER_EMAIL='EMAIL_DEL_USUARIO_DE_PRUEBA'
export TEST_USER_PASSWORD='PASSWORD_DEL_USUARIO_DE_PRUEBA'
```

## Crear y clonar tu repositorio

1. En GitHub, seleccioná **Use this template** y después **Create a new repository**.
2. Marcá **Include all branches** si querés recibir también la rama `solution`.
3. Copiá la URL de tu repositorio y ejecutá:

```bash
git clone URL_DE_TU_REPOSITORIO
cd NOMBRE_DE_TU_REPOSITORIO
git branch --all
git switch main
```

Trabajá sobre `main`. Cuando el curso indique consultar la solución, podés cambiar de rama:

```bash
git switch solution
```

Para regresar a tu trabajo:

```bash
git switch main
```

Si no usás GitHub, podés descargar la rama que necesites mediante **Code → Download ZIP**. El ZIP no incluye historial ni sincronización con Git.

## Verificar el entorno

Abrí una terminal en la carpeta raíz del proyecto, donde están `build.gradle`, `gradlew` y `gradlew.bat`.

En macOS o Linux:

```bash
java -version
./gradlew --version
./gradlew doctor
```

En PowerShell:

```powershell
java -version
.\gradlew.bat --version
.\gradlew.bat doctor
```

En CMD:

```bat
java -version
gradlew.bat --version
gradlew.bat doctor
```

La salida debe mostrar Java 21. La primera ejecución del Wrapper puede descargar Gradle y tardar un poco más.

### Comprobar Selenium y Chrome

El chequeo técnico es intencional y está separado de la suite normal:

```bash
./gradlew setupCheck
```

En PowerShell usá `.\gradlew.bat setupCheck`; en CMD, `gradlew.bat setupCheck`.

`setupCheck` deja que Selenium Manager resuelva ChromeDriver, abre Chrome, verifica una página local y cierra el navegador. No hace falta descargar ChromeDriver manualmente.

## Ejecutar las pruebas

### Todos los escenarios

macOS o Linux:

```bash
./gradlew test
```

PowerShell:

```powershell
.\gradlew.bat test
```

CMD:

```bat
gradlew.bat test
```

### Filtrar por tags

El proyecto usa este contrato:

```text
-PcucumberTags='expresión' → tarea test → cucumber.filter.tags → Cucumber
```

Un tag:

```bash
./gradlew test -PcucumberTags='@smoke'
```

Expresiones útiles:

```bash
./gradlew test -PcucumberTags='@smoke and not @wip'
./gradlew test -PcucumberTags='@smoke or @regression'
./gradlew test -PcucumberTags='(@smoke or @regression) and not @wip'
```

En PowerShell reemplazá `./gradlew` por `.\gradlew.bat`. En CMD usá `gradlew.bat` y comillas dobles:

```bat
gradlew.bat test -PcucumberTags="@smoke or @regression"
```

Usá siempre comillas normales alrededor de la expresión, especialmente si contiene espacios o paréntesis.

### Repetir una demostración

Si Gradle informa `UP-TO-DATE`, forzá una nueva ejecución con:

```bash
./gradlew test --rerun-tasks -PcucumberTags='@smoke'
```

`--rerun-tasks` es útil para diagnóstico o demostraciones; no hace falta agregarlo a cada ejecución.

## Verificar cuántos escenarios corrieron

Un `BUILD SUCCESSFUL` no alcanza para demostrar que el filtro funcionó: la ejecución también puede haber encontrado cero escenarios.

Después de cada comando:

1. Revisá el resumen de Cucumber en la terminal.
2. Confirmá el número de escenarios ejecutados.
3. Abrí los reportes generados.

Reportes principales:

- TestNG/Gradle: `build/reports/tests/test/index.html`.
- Cucumber HTML: `build/reports/cucumber/cucumber.html`.
- Cucumber JSON: `build/reports/cucumber/cucumber.json`.
- Cucumber JUnit XML para CI: `build/test-results/cucumber/cucumber.xml`.

## Integración continua con GitHub Actions

El workflow `.github/workflows/tests.yml` ejecuta la suite completa en cada `push`, en cada `pull_request` y cuando se inicia manualmente desde **Actions → Pruebas Selenium → Run workflow**.

Antes de la primera ejecución, creá estos dos secretos en **Settings → Secrets and variables → Actions**:

- `TEST_USER_EMAIL`
- `TEST_USER_PASSWORD`

GitHub Actions usa Java 21, el Gradle Wrapper del repositorio y Chrome en modo headless. Al finalizar, conserva durante siete días un artifact llamado `reportes-pruebas` con los reportes HTML, JSON, JUnit XML y TestNG/Gradle, incluso cuando la suite falla.

Los secretos de un repositorio no se comparten con workflows disparados por pull requests desde forks. En ese caso, la verificación de credenciales falla de forma explícita antes de abrir Chrome.

## Problemas frecuentes

### Gradle dice que la tarea no existe

Confirmá que la terminal esté en la raíz del proyecto y listá las tareas disponibles:

```bash
./gradlew tasks --group verification
```

### El build termina bien, pero ejecuta cero escenarios

- Confirmá que el runner extienda `AbstractTestNGCucumberTests`.
- Confirmá que la tarea `test` use TestNG.
- Revisá las rutas de `features` y `glue` en el runner.
- Revisá que el tag exista, incluyendo la arroba.
- Ejecutá con información adicional:

```bash
./gradlew test --info -PcucumberTags='@smoke'
```

### Ejecuta todos los escenarios e ignora el filtro

- Usá `-P` con `P` mayúscula.
- Escribí exactamente `cucumberTags`.
- No agregues un filtro `tags = "..."` fijo en `@CucumberOptions`.
- Conservá la expresión completa entre comillas.

### Otros problemas de setup

- **Java no es 21:** instalá JDK 21 y volvé a abrir la terminal.
- **`JAVA_HOME` es incorrecto:** apuntalo al JDK 21 y repetí `java -version` y el diagnóstico.
- **`Permission denied` al usar `gradlew`:** ejecutá `chmod +x gradlew`.
- **Chrome no está instalado:** instalalo y repetí `setupCheck`.
- **Selenium Manager no resuelve ChromeDriver:** revisá la conexión, el proxy o el firewall. No configures un driver manualmente.
- **Falla solamente en una terminal:** evitá comillas tipográficas y copiá el comando y el error completos.

## Estructura de referencia de `solution`

```text
src/test/java/
├── components/
├── driver/
├── hooks/
├── models/
├── pages/
├── runner/
├── setup/
├── support/
└── steps/

src/test/resources/
├── cucumber.properties
└── features/
```

- `driver` administra el ciclo de vida de WebDriver.
- `hooks` abre y cierra Chrome por escenario y adjunta una captura cuando hay un fallo.
- `pages` contiene acciones y estado de las páginas, sin assertions.
- `steps` conecta Gherkin con los Page Objects y realiza las verificaciones.
- `runner` integra Cucumber con TestNG y configura los reportes.
- `support` contiene el único usuario de prueba compartido por los escenarios.
- `features` contiene los escenarios y sus tags.
