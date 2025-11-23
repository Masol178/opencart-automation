# language: es
@smoke @busqueda
Característica: Búsqueda de Productos en OpenCart
  @regression
  Escenario: Búsqueda exitosa de un producto existente
    Dado estar en la página principal de OpenCart
    Cuando buscar el producto "iPhone"
    Entonces verificar que aparecen resultados de búsqueda para "iPhone"
    Y el primer producto contiene "iPhone" en su nombre

  Escenario: Búsqueda con múltiples resultados
    Dado estar en la página principal de OpenCart
    Cuando buscar el producto "MacBook"
    Entonces verificar que aparecen resultados de búsqueda
    Y verificar que hay al menos 1 producto en los resultados

  Escenario: Ver detalles de un producto desde la búsqueda
    Dado estar en la página principal de OpenCart
    Cuando buscar el producto "MacBook"
    Y hacer clic en el primer producto de los resultados
    Entonces verificar que se muestra la página de detalles del producto
    Y verificar que el producto tiene un botón "Add to Cart"
