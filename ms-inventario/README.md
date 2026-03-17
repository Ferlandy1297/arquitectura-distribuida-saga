ms-inventario

Descripción
- Microservicio encargado de gestionar el stock de productos.
- BD H2 en memoria y datos iniciales: Laptop=10, Mouse=20, Teclado=15.

Endpoints
- GET `/inventario`
- POST `/inventario/reservar` — body: `{ "producto": "Laptop", "cantidad": 2 }`
- POST `/inventario/liberar` — body: `{ "producto": "Laptop", "cantidad": 1 }`

Ejemplos
- Reservar OK (200):
  Request: `{ "producto": "Mouse", "cantidad": 3 }`
  Response: `{ "success": true, "message": "Stock reservado", "producto": "Mouse", "cantidad": 3, "stockActual": 17 }`

- Error producto no existe (404):
  Response: `{ "success": false, "message": "Producto no encontrado: XYZ" }`

- Error stock insuficiente (400):
  Response: `{ "success": false, "message": "Stock insuficiente para Laptop" }`

