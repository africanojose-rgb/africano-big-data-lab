# AFRICANO BIG DATA LAB

Backend desarrollado con Java y Spring Boot como proyecto de aprendizaje, práctica profesional y portafolio orientado al desarrollo Backend y procesamiento de datos.

El proyecto evoluciona progresivamente desde una API REST conectada a PostgreSQL hacia una arquitectura orientada al procesamiento y análisis de datos utilizando Apache Spark.

## 🎯 Objetivo

Construir una aplicación backend profesional que permita aplicar y demostrar conocimientos en:

* Java 17
* Spring Boot
* Desarrollo de APIs REST
* Spring Data JPA
* Hibernate
* PostgreSQL
* SQL
* Validación de datos
* Arquitectura por capas
* Testing
* Git y GitHub
* Apache Spark
* Procesamiento de Big Data

El proyecto se desarrolla de forma incremental, incorporando nuevas funcionalidades a medida que se consolida la arquitectura backend.

---

## 🛠️ Tecnologías

| Tecnología      | Versión / Uso        |
| --------------- | -------------------- |
| Java            | 17                   |
| Spring Boot     | 4.0.7                |
| Maven           | Maven Wrapper        |
| Spring Web MVC  | API REST             |
| Spring Data JPA | Persistencia         |
| Hibernate       | ORM                  |
| PostgreSQL      | 16.14                |
| JUnit           | Testing              |
| Git             | Control de versiones |
| GitHub          | Repositorio remoto   |
| Apache Spark    | Próxima etapa        |

---

## 🏗️ Arquitectura actual

Actualmente el backend cuenta con una estructura inicial preparada para evolucionar hacia una arquitectura por capas.

```text
backend/
├── pom.xml
├── .env.example
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── africano/
    │   │           └── backend/
    │   │               ├── BackendApplication.java
    │   │               │
    │   │               └── controller/
    │   │                   └── HealthController.java
    │   │
    │   └── resources/
    │       └── application.properties
    │
    └── test/
        └── java/
            └── com/
                └── africano/
                    └── backend/
                        └── BackendApplicationTests.java
```

Las capas de dominio, persistencia y servicios se incorporarán progresivamente durante el desarrollo del proyecto.

---

## ⚙️ Requisitos

Para ejecutar el proyecto localmente se requiere:

* Java 17
* PostgreSQL 16 o compatible
* Git
* Sistema operativo Linux, macOS o Windows
* Una cuenta de GitHub para clonar el repositorio

---

## 🗄️ Configuración de PostgreSQL

Crear una base de datos PostgreSQL:

```sql
CREATE DATABASE africano_big_data;
```

Crear el usuario de aplicación:

```sql
CREATE USER africano_app WITH PASSWORD 'tu_password';
```

Conceder permisos sobre la base de datos:

```sql
GRANT ALL PRIVILEGES ON DATABASE africano_big_data TO africano_app;
```

La configuración específica de conexión debe realizarse mediante variables de entorno.

---

## 🔐 Variables de entorno

Las credenciales de la base de datos no se almacenan directamente en el repositorio.

Crear un archivo `.env` dentro de `backend/` basado en:

```text
backend/.env.example
```

Ejemplo:

```properties
DB_URL=jdbc:postgresql://localhost:5432/africano_big_data
DB_USERNAME=africano_app
DB_PASSWORD=tu_password
```

El archivo `.env` real está excluido del control de versiones mediante `.gitignore`.

---

## ▶️ Ejecutar el proyecto

Desde el directorio `backend`:

```bash
cd backend
```

Cargar las variables de entorno:

```bash
set -a
source .env
set +a
```

Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Ejecutar tests

Para limpiar, compilar y ejecutar las pruebas:

```bash
./mvnw clean test
```

El proyecto debe finalizar con:

```text
BUILD SUCCESS
```

Actualmente se cuenta con una prueba de contexto de Spring Boot que verifica que la aplicación puede iniciar correctamente y establecer la conexión con PostgreSQL.

---

## ❤️ Health Check

El backend cuenta con un endpoint básico de health check para verificar que la API está disponible.

Endpoint:

```text
GET /api/health

---

## 🧭 Roadmap

El proyecto se desarrollará progresivamente siguiendo las siguientes etapas:

### Etapa 1 — Configuración inicial

* [x] Crear proyecto Spring Boot
* [x] Configurar Java 17
* [x] Configurar Maven
* [x] Configurar PostgreSQL
* [x] Configurar variables de entorno
* [x] Crear endpoint de health check
* [x] Configurar Git
* [x] Configurar repositorio GitHub

### Etapa 2 — Profesionalización del backend

* [x] Limpiar dependencias innecesarias
* [x] Eliminar Thymeleaf
* [x] Simplificar configuración Maven
* [x] Configurar `.env.example`
* [x] Proteger credenciales
* [x] Unificar configuración `.gitignore`
* [x] Configurar JPA
* [x] Configurar tests
* [x] Documentar el proyecto

### Etapa 3 — Persistencia y API REST

* [ ] Diseñar modelo de datos
* [ ] Crear entidades JPA
* [ ] Crear repositories
* [ ] Crear services
* [ ] Crear DTOs
* [ ] Implementar validaciones
* [ ] Implementar manejo global de excepciones
* [ ] Construir operaciones CRUD
* [ ] Crear documentación de la API

### Etapa 4 — SQL y PostgreSQL

* [ ] Consultas SQL avanzadas
* [ ] Relaciones entre entidades
* [ ] Índices
* [ ] Optimización de consultas
* [ ] Transacciones
* [ ] Paginación y ordenamiento

### Etapa 5 — Big Data

* [ ] Introducción a Apache Spark
* [ ] Spark con Java
* [ ] DataFrames
* [ ] Transformaciones
* [ ] Acciones
* [ ] Agrupaciones y agregaciones
* [ ] Procesamiento de grandes volúmenes de datos

### Etapa 6 — Integración Backend + Big Data

* [ ] Integrar procesamiento de datos con Spark
* [ ] Diseñar flujo de procesamiento
* [ ] Procesar datos provenientes de PostgreSQL
* [ ] Generar análisis y métricas
* [ ] Exponer resultados mediante API REST

---

## 📌 Estado actual

El proyecto se encuentra en fase de construcción de la arquitectura base.

Actualmente cuenta con:

* Backend Spring Boot funcional
* Java 17
* API REST inicial
* PostgreSQL conectado
* Spring Data JPA
* Hibernate
* Configuración mediante variables de entorno
* Health Check
* Tests automatizados
* Repositorio GitHub
* Configuración preparada para futuras etapas de Big Data

---

## 👨‍💻 Autor

**José Luis Africano Moya**

Proyecto personal de aprendizaje y portafolio orientado al desarrollo Backend con Java y tecnologías de procesamiento de datos.

---

## 📄 Licencia

Este proyecto se encuentra actualmente en desarrollo y destinado a fines educativos, de aprendizaje y portafolio profesional.
