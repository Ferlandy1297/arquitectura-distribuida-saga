ms-pedidos

Descripción
- Orquestador de la saga (fase 1). Crea pedidos e invoca ms-inventario para reservar stock.

Endpoints
- POST `/pedidos` — body: `{ "producto": "Laptop", "cantidad": 2 }`
- GET `/pedidos`
- GET `/pedidos/{id}`

Ejemplos
- Crear pedido exitoso (200):
  Request: `{ "producto": "Laptop", "cantidad": 2 }`
  Response: `{ "id": 1, "producto": "Laptop", "cantidad": 2, "estado": "COMPLETADO" }`

- Crear pedido con stock insuficiente (200, cancelado):
  Request: `{ "producto": "Laptop", "cantidad": 1000 }`
  Response: `{ "id": 2, "producto": "Laptop", "cantidad": 1000, "estado": "CANCELADO" }`

