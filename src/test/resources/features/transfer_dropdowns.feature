# language: es
Característica: Seleccionar cuentas para una transferencia

  Esquema del escenario: Elegir cuentas por su posición sin transferir dinero
    Dado que estoy en la página de login de BuggyBank
    Y inicio sesión con el email "<email>" y la contraseña "<password>"
    Y estoy en la página de transferencias
    Cuando selecciono la segunda opción del dropdown de cuenta origen
    Y selecciono la primera opción del dropdown de cuenta destino
    Entonces la segunda opción queda seleccionada como cuenta origen
    Y la primera opción queda seleccionada como cuenta destino

    Ejemplos:
      | email                           | password             |
      | tester-12e0e175@buggybank.local | vsjroxlPY_We1WjXiAx7 |
