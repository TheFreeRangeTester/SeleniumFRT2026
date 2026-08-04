# language: es
Característica: Inicio de sesión

  Esquema del escenario: Un usuario válido puede ingresar al dashboard
    Dado que estoy en la página de login de BuggyBank
    Cuando inicio sesión con el email "<email>" y la contraseña "<password>"
    Entonces veo el dashboard de "<nombre>"

    Ejemplos:
      | email                               | password                  | nombre           |
      | tester-12e0e175@buggybank.local     | vsjroxlPY_We1WjXiAx7      | Temporary Tester |
