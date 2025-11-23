# language: es
@smoke @registro
Característica: Registro de Usuario en OpenCart
  @regression
  Escenario: Registro exitoso de un nuevo usuario
    Dado estar en la página principal de OpenCart
    Cuando navegar a la página de registro
    Y completar el formulario de registro con datos válidos:
      | nombre    | Maria Loreto         |
      | apellido  | Solorza              |
      | correo    | pruebawalmart1@ejemplo.com   |
      | telefono  | +56912345678         |
      | clave     | Prueba123456!        |
    Y aceptar la política de privacidad
    Y hacer clic en el botón Continue
    Entonces verificar que aparece el mensaje de registro exitoso
    Y verificar que se redirige a mi cuenta

  Escenario: Intento de registro sin aceptar política de privacidad
    Dado estar en la página principal de OpenCart
    Cuando navegar a la página de registro
    Y completar el formulario de registro con datos válidos:
      | nombre    | Maria Loreto         |
      | apellido  | Solorza              |
      | correo    | pruebawalmart2@ejemplo.com  |
      | telefono  | +56912345679         |
      | clave     | Prueba123456!        |
    Y NO aceptar la política de privacidad
    Y hacer clic en el botón Continue
    Entonces verificar que aparece un mensaje de error sobre la política de privacidad

  Escenario: Validación de campos obligatorios en registro
    Dado estar en la página principal de OpenCart
    Cuando navegar a la página de registro
    Y hacer clic en el botón Continue sin llenar campos
    Entonces verificar que aparecen mensajes de error en los campos obligatorios
