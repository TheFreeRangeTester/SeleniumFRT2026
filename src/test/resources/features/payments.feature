# language: es
@pagos
Característica: Gestión de pagos

  @smoke @ui
  Esquema del escenario: Consultar y filtrar los pagos por estado
    Dado que el usuario de prueba inició sesión en BuggyBank
    Y estoy en la página de pagos
    Entonces el filtro de pagos ofrece los estados "Todos, Programado, Pendiente, Completado, Revertido"
    Y el enlace para crear un pago apunta a "/payments/new"
    Cuando filtro los pagos por estado "<estado>"
    Entonces veo pagos y todos tienen el estado "<estado>"

    Ejemplos:
      | estado      |
      | Programado  |
      | Pendiente   |
      | Completado  |
      | Revertido   |