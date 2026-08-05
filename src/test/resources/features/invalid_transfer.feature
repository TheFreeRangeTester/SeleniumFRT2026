# language: es
Característica: Ejercicio 2 - Validar una transferencia inválida

  Esquema del escenario: Impedir una transferencia hacia la misma cuenta
    Dado que Maria inició sesión en BuggyBank con el email "<email>" y la contraseña "<password>"
    Y estoy en la página de transferencias
    Cuando elijo la misma cuenta como origen y destino
    Y completo la transferencia con el monto "100" y la descripción "Same account validation"
    Y intento enviar la transferencia
    Entonces veo el error de cuenta destino "La cuenta destino debe ser distinta"

    Ejemplos:
      | email                           | password             |
      | tester-908cbacd@buggybank.local | CHedUCH4f0Zq84zlOB06 |
