# language: es
Característica: Ejercicio 3 - Navegación principal

  Esquema del escenario: La navegación principal conecta todas las áreas de BuggyBank
    Dado que Maria inició sesión en BuggyBank con el email "<email>" y la contraseña "<password>"
    Cuando recorre las secciones principales
    Entonces cada sección muestra su URL y contenido esperado

    Ejemplos:
      | email                           | password             |
      | tester-908cbacd@buggybank.local | CHedUCH4f0Zq84zlOB06 |
