@regresion
Feature: Búsqueda y operaciones en DuckDuckGo

  @DuckDuckOperaciones
  Scenario Outline: Realizar operaciones matemáticas
    Given el usuario está en la página de DuckDuckGo
    When el usuario busca "calculadora" en la barra de búsqueda
    Then el usuario debería ver resultados relacionados con "calculator"

    When el usuario realiza la operación "<operacion>" con <numero1> y <numero2>
    Then el resultado debería ser <resultado>

  Examples:
    | operacion      | numero1 | numero2 | resultado |
    | suma           | 2       | 2       | 4         |
    | resta          | 5       | 3       | 2         |
    | multiplicacion | 3       | 4       | 12        |
    | división       | 8       | 2       | 4         |
