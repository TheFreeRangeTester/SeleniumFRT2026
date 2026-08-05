# language: es
Característica: Ejercicio 1 - Buscar movimientos

  Esquema del escenario: Buscar una transferencia saliente por descripción
    Dado que Maria inició sesión en BuggyBank con el email "<email>" y la contraseña "<password>"
    Y está en la página de movimientos
    Cuando filtra los movimientos por "Salientes"
    Y busca el texto "Limit test"
    Entonces ve al menos un resultado
    Y todos los resultados visibles son salidas
    Y todas las descripciones visibles contienen "Limit test"

    Ejemplos:
      | email                           | password             |
      | tester-908cbacd@buggybank.local | CHedUCH4f0Zq84zlOB06 |
