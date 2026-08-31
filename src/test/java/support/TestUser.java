package support;

/**
 * Usuario compartido por los escenarios automatizados del curso.
 */
public final class TestUser {

    public static final String EMAIL = requiredEnvironmentVariable("TEST_USER_EMAIL");
    public static final String PASSWORD = requiredEnvironmentVariable("TEST_USER_PASSWORD");

    private TestUser() {
        // Evita crear instancias de esta clase de datos.
    }

    private static String requiredEnvironmentVariable(String name) {
        String value = System.getenv(name);

        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Falta la variable de entorno requerida " + name
                            + ". Configurala antes de ejecutar las pruebas."
            );
        }

        return value;
    }
}
