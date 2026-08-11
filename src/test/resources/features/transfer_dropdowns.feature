# language: es
Característica: Seleccionar cuentas para una transferencia

  Escenario: Elegir cuentas por su posición sin transferir dinero
    Dado que estoy en la página de login de BuggyBank
    Y inicio sesión con el usuario de prueba
    Y estoy en la página de transferencias
    Cuando selecciono la segunda opción del dropdown de cuenta origen
    Y selecciono la primera opción del dropdown de cuenta destino
    Entonces la segunda opción queda seleccionada como cuenta origen
    Y la primera opción queda seleccionada como cuenta destino
