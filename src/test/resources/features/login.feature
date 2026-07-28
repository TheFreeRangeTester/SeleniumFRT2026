# language: es
Característica: Inicio de sesión

  Esquema del escenario: Un usuario válido puede ingresar al dashboard
    Dado que estoy en la página de login de BuggyBank
    Cuando inicio sesión con el email "<email>" y la contraseña "<password>"
    Entonces veo el dashboard de "<nombre>"

    Ejemplos:
      | email                     | password | nombre         |
      | maria@buggybank.local     | Pass1234 | Maria Thompson |
      | diego@buggybank.local     | Pass1234 | Diego Alvarez  |
      | sofie@buggybank.local     | Pass1234 | Sofie Patel    |
