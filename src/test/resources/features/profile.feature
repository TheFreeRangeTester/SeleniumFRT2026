# language: es

Característica: Soft assertions sobre el perfil sembrado

  @soft
  Escenario: El perfil muestra todos los datos sembrados del usuario
    Dado que el usuario de prueba inició sesión en BuggyBank
    Cuando abre su perfil
    Entonces el perfil contiene los datos sembrados del usuario
