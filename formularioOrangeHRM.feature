@OrangeHRM
Feature: Llenado del formulario en OrangeHRM

  Scenario Outline: Llenar y enviar el formulario en OrangeHRM
    Given el usuario está en la página del formulario de OrangeHRM
    When el usuario da clic en cerrar alerta
    When el usuario llena el campo nombre con "<Nombre>"
    When el usuario llena el campo telefono con "<Telefono>"
    When el usuario llena el campo email con "<Email>"
    When el usuario llena el campo empresa con "<Empresa>"
    When el usuario selecciona el pais "Afghanistan"
    When el usuario selecciona el numero de empleados "11 - 50"

  Examples:
    | Nombre | Telefono   | Email                | Empresa       |
    | Camilo | 1234567890 | camilo.perez@test.com| MiEmpresa S.A.|
