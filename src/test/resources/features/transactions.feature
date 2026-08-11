# language: es
@transferencias
Característica: Ejercicio 1 - Buscar movimientos

  @smoke @ui
  Escenario: Buscar una transferencia saliente por descripción
    Dado que el usuario de prueba inició sesión en BuggyBank
    Y está en la página de movimientos
    Cuando filtra los movimientos por "Salientes"
    Y busca el texto "Limit test"
    Entonces ve al menos un resultado
    Y todos los resultados visibles son salidas
    Y todas las descripciones visibles contienen "Limit test"
