Arquitectura Distribuida — Saga por Orquestación (Fase 1)

Objetivo
- Preparar dos microservicios dummy con Java 17 y Spring Boot 3, comunicados vía REST, dejando lista la base para integrar la Saga completa en fases posteriores (pagos y notificaciones).

Arquitectura Actual
- ms-pedidos (orquestador inicial): crea pedidos y orquesta la reserva de stock en inventario usando OpenFeign.
- ms-inventario: gestiona el stock y expone operaciones de reservar y liberar.
- Comunicación: REST/HTTP (OpenFeign en ms-pedidos).
- BDs: H2 en memoria por microservicio.

Puertos
- ms-pedidos: 8081
- ms-inventario: 8082

Cómo Ejecutar
- Requisitos: Java 17 y Maven.
- Compilar todo (una vez en la raíz):
  - `mvn -DskipTests package`
- Ejecutar ms-inventario:
  - `mvn -pl ms-inventario spring-boot:run`
- Ejecutar ms-pedidos (en otra terminal):
  - `mvn -pl ms-pedidos spring-boot:run`

Flujo Actual (Orquestación simple)
1) ms-pedidos crea un pedido en estado PENDIENTE.
2) ms-pedidos llama a ms-inventario para reservar stock.
3) Si inventario confirma, ms-pedidos actualiza a COMPLETADO.
4) Si inventario falla (404/400), ms-pedidos lo marca CANCELADO.

Endpoints Principales
- ms-inventario (8082)
  - GET `/inventario` — lista de productos y stock
  - POST `/inventario/reservar` — body: `{ producto, cantidad }`
  - POST `/inventario/liberar` — body: `{ producto, cantidad }`
- ms-pedidos (8081)
  - POST `/pedidos` — body: `{ producto, cantidad }`
  - GET `/pedidos` — lista de pedidos
  - GET `/pedidos/{id}` — detalle por id

Postman
- Carpeta `postman/` contiene:
  - `arquitectura-distribuida-fase1.postman_collection.json`
  - `arquitectura-distribuida-local.postman_environment.json`
- Variables: host y puertos. Incluye tests básicos (status code y campos).

Estructura
- `README.md`
- `postman/`
- `ms-inventario/`
- `ms-pedidos/`

Próximos Pasos (Fase 2)
- Integrar ms-pagos y coordinar confirmación/compensación.
- Integrar ms-notificaciones para avisos de estado de pedidos.
- Añadir correlación/trace IDs y timeouts/retries en Feign.
- Persistir eventos y agregar métricas.

