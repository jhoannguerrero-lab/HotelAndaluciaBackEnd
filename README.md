# Hotel Andalucía — Backend (API REST)

API REST del sistema de administración del Hotel Andalucía. Gestiona
habitaciones y reservas (recursos relacionados), con autenticación por
JWT y autorización por roles.

## Stack

- Java 17 + Spring Boot 3.3.4
- Spring Web (API REST)
- Spring Data JPA + Hibernate (acceso a datos)
- Spring Security + JWT (`jjwt`) (autenticación y autorización)
- MySQL / MariaDB

## Requisitos previos

| Herramienta | Versión | Verificar con |
|---|---|---|
| JDK | 17 o 21 | `java -version` |
| Maven | (opcional, el proyecto trae `mvnw`) | `mvn -v` |
| MySQL / MariaDB | 8.x / 10.x | `mysql -u root -p` |

## Instalación y ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/hotel-andalucia-backend.git
cd hotel-andalucia-backend
```

### 2. Crear la base de datos
No es obligatorio hacerlo a mano — `application.properties` tiene
`createDatabaseIfNotExist=true`, se crea sola al arrancar. Si prefieres
crearla tú mismo:
```sql
CREATE DATABASE hotel_andalucia CHARACTER SET utf8mb4;
```

### 3. Configurar credenciales
Edita `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=TU_CONTRASENA
```

### 4. Ejecutar la aplicación
```bash
./mvnw spring-boot:run
```
O desde tu IDE: ejecuta `BackendApplication.java`.

La API queda disponible en `http://localhost:8080`. Al primer arranque,
Hibernate crea las tablas y `data.sql` carga los datos de ejemplo
(2 usuarios, 8 habitaciones, 5 reservas).

## Usuarios de prueba

| Email | Contraseña | Rol | Permisos |
|---|---|---|---|
| `admin@hotelandalucia.com` | `Gestor123!` | `GESTOR` | Ver, crear, editar y eliminar |
| `recepcion@hotelandalucia.com` | `Consulta123!` | `CONSULTA` | Solo ver |

## Autenticación

Todas las rutas bajo `/api/habitaciones` y `/api/reservas` requieren un
token JWT. Se obtiene haciendo login y se envía en cada petición
posterior como header:
```
Authorization: Bearer {token}
```

## Endpoints

### Autenticación

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| POST | `/api/auth/login` | Público | Autentica y devuelve el token JWT |

**Request:**
```json
{ "email": "admin@hotelandalucia.com", "password": "Gestor123!" }
```
**Response `200`:**
```json
{ "token": "eyJhbGciOiJIUzI1NiJ9...", "nombreCompleto": "Administrador General", "rol": "GESTOR" }
```
**Response `401`** (credenciales incorrectas):
```json
{ "error": "Email o contrasena incorrectos" }
```

### Habitaciones

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| GET | `/api/habitaciones` | CONSULTA o GESTOR | Lista todas las habitaciones |
| GET | `/api/habitaciones/{id}` | CONSULTA o GESTOR | Detalle de una habitación |
| POST | `/api/habitaciones` | Solo GESTOR | Crea una habitación |
| PUT | `/api/habitaciones/{id}` | Solo GESTOR | Actualiza una habitación |
| DELETE | `/api/habitaciones/{id}` | Solo GESTOR | Elimina una habitación |

**Request (POST/PUT):**
```json
{
  "numero": "401",
  "tipo": "SUITE_FAMILIAR",
  "piso": 4,
  "capacidad": 4,
  "precioNoche": 850.00,
  "estado": "DISPONIBLE"
}
```
Valores válidos — `tipo`: `INDIVIDUAL`, `DOBLE`, `DOBLE_VISTA_MAR`, `SUITE_FAMILIAR`. `estado`: `DISPONIBLE`, `OCUPADA`, `MANTENIMIENTO`.

### Reservas

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| GET | `/api/reservas` | CONSULTA o GESTOR | Lista todas las reservas |
| GET | `/api/reservas/{id}` | CONSULTA o GESTOR | Detalle de una reserva |
| POST | `/api/reservas` | Solo GESTOR | Crea una reserva (enlazada a una habitación) |
| PUT | `/api/reservas/{id}` | Solo GESTOR | Actualiza una reserva |
| DELETE | `/api/reservas/{id}` | Solo GESTOR | Elimina una reserva |

**Request (POST/PUT):**
```json
{
  "habitacionId": 4,
  "huespedNombre": "Maria Fernanda Rojas",
  "huespedDocumento": "8452136 LP",
  "huespedTelefono": "71234567",
  "checkin": "2026-09-10",
  "checkout": "2026-09-13",
  "numHuespedes": 2,
  "metodoPago": "TARJETA",
  "estado": "CONFIRMADA",
  "notas": "Llegada nocturna"
}
```
Valores válidos — `metodoPago`: `TARJETA`, `TRANSFERENCIA`, `EFECTIVO`. `estado`: `CONFIRMADA`, `PENDIENTE`, `FINALIZADA`, `CANCELADA`.

Si `habitacionId` no corresponde a una habitación existente, responde
`400` con `"La habitacion indicada no existe"`.

## Estructura del proyecto

```
src/main/java/com/hotelandalucia/backend/
├── model/          Entidades JPA (Usuario, Habitacion, Reserva) y enums
├── repository/      Interfaces Spring Data JPA
├── security/         SecurityConfig, JwtService, JwtAuthFilter
├── controller/     AuthController, HabitacionController, ReservaController
├── service/          AuthService
├── dto/                Request/Response de cada recurso
├── exception/       Manejo global de errores de validacion
└── config/           Configuracion de CORS
```

## Flujo de Git

`main` (producción) ← `release/v1.0.0` ← `develop` ← `feature/setup`,
`feature/auth-roles`, `feature/crud`. Detalle completo de ramas y
commits en `GUIA_INSTALACION_GIT.md`.
