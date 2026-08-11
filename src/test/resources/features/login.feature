# language: es
Característica: Inicio de sesión

  @smoke
  Escenario: Un usuario válido puede ingresar al dashboard
    Dado que estoy en la página de login de BuggyBank
    Cuando inicio sesión con el usuario de prueba
    Entonces veo el dashboard de "Temporary Tester"
