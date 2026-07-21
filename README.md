# Selenium WebDriver con Java y Cucumber — starter

Este repositorio deja lista la infraestructura del curso. No contiene el framework ni la solución de los ejercicios: los vas a construir durante las clases.

## Prerrequisitos

- JDK 21 (comprobá que `JAVA_HOME` apunte a ese JDK).
- Google Chrome.
- Git.
- VS Code recomendado, con el Extension Pack for Java sugerido al abrir el proyecto.

No hace falta instalar Gradle globalmente: el repositorio incluye Gradle Wrapper 9.6.1.

## Crear y clonar tu repositorio

1. En GitHub, pulsá **Use this template** y luego **Create a new repository**.
2. Elegí el nombre y la visibilidad. **No selecciones “Include all branches”.**
3. Copiá la URL de tu repositorio y clonalo:

```bash
git clone URL_DE_TU_REPOSITORIO
cd NOMBRE_DE_TU_REPOSITORIO
```

Si no tenés cuenta de GitHub, podés usar **Code → Download ZIP**, descomprimir el archivo y abrir esa carpeta. En ese caso no tendrás historial ni sincronización mediante Git.

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

El starter ya incluye Java Toolchain 21, Gradle Wrapper, Selenium, Cucumber integrado con TestNG, descubrimiento de tests y el diagnóstico `doctor`.

Durante el curso vas a crear los feature files, escenarios, step definitions, hooks, Page Objects y el resto del framework. Nada de eso viene resuelto acá.
