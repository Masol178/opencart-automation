# language: es
@smoke @carrito
Característica: Proceso de Compra en OpenCart
  @regression
  Escenario: Agregar un producto al carrito desde resultados de búsqueda
    Dado estar en la página principal de OpenCart
    Cuando buscar el producto "MacBook"
    Y agregar el primer producto al carrito
    Entonces verificar que aparece un mensaje de confirmación
    Y verificar que el carrito muestra 1 item

  Escenario: Ver el carrito de compras con productos
    Dado estar en la página principal de OpenCart
    Y haber agregado un producto "iPhone" al carrito
    Cuando navegar al carrito de compras
    Entonces verificar que aparece el producto "iPhone" en el carrito
    Y verificar que aparece el precio total del carrito
    Y verificar que aparece el botón "Checkout"

  Escenario: Modificar cantidad de producto en el carrito
    Dado estar en la página principal de OpenCart
    Y haber agregado un producto "MacBook" al carrito
    Cuando navegar al carrito de compras
    Y cambiar la cantidad del producto a 2
    Y actualizar el carrito
    Entonces verificar que el subtotal refleja la nueva cantidad
    Y verificar que el carrito muestra 2 items del mismo producto

  Escenario: Eliminar un producto del carrito
    Dado estar en la página principal de OpenCart
    Y haber agregado un producto "iPhone" al carrito
    Cuando navegar al carrito de compras
    Y eliminar el producto del carrito
    Entonces verificar que el carrito está vacío
