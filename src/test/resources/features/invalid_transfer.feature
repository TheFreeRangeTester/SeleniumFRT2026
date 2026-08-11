# language: es
Característica: Ejercicio 2 - Validar una transferencia inválida

  Escenario: Impedir una transferencia hacia la misma cuenta
    Dado que el usuario de prueba inició sesión en BuggyBank
    Y estoy en la página de transferencias
    Cuando elijo la misma cuenta como origen y destino
    Y completo la transferencia con el monto "100" y la descripción "Same account validation"
    Y intento enviar la transferencia
    Entonces veo el error de cuenta destino "La cuenta destino debe ser distinta"
