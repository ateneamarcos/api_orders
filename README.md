# api_orders

Ejecución con Docker
Construye la imagen:

docker build -t demo-app:latest .

Ejecuta el contenedor:

docker run --name demo-container -d -p 8080:8080 demo-app:latest


Características principales
API REST para gestión de órdenes y conductores

Asignación de conductores a órdenes (validando que estén activos y que la orden esté en estado CREATED)

Subida de archivos PDF e imágenes (.png, .jpg)

Validaciones en DTOs con @Valid y javax.validation.constraints

Seguridad básica con Spring Security (UWT básico)

Documentación con Swagger / OpenAPI

Pruebas unitarias con JUnit 5 y Mockito

Mapping entre DTOs y entidades usando MapStruct (o manual mapping)

Manejo global de excepciones con @ControllerAdvice

Logging estructurado



Ejecutar pruebas unitarias



Con Maven:

./mvnw test

Documentación de la API


Una vez la app esté corriendo, consulta la documentación Swagger:

http://localhost:8080/swagger-ui.html

Autor
Atenea Nathaly Marcos Lopez

## Endpoints de la API

### Ordenes

| Método | Endpoint | Descripción |
|-------|----------|-------------|
| POST | /orders | Crear una nueva orden |
| PUT | /orders/{id}/status | Actualizar el estado de una orden |
| GET | /orders/{id} | Consultar orden por ID |
| PUT | /orders/{id}/assign-driver/{driverId} | Asignar un conductor a la orden (solo si está activo y la orden está en estado CREATED) |
| POST | /orders/{id}/attachments | Agregar archivo o imagen a la orden |

---

### Conductores

| Método | Endpoint | Descripción |
|-------|----------|-------------|
| POST | /drivers | Crear un nuevo conductor |
| GET | /drivers/active | Listar todos los conductores activos |

---

### 📖 Notas

- Las rutas pueden devolver errores controlados si los datos no cumplen validaciones.
- El acceso puede estar protegido por autenticación básica (Spring Security).
- Los archivos adjuntos pueden ser PDF, PNG o JPG.
