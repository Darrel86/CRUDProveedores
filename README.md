# CRUD de Proveedores – Corporación Favorita

## Descripción del proyecto

Este proyecto consiste en el desarrollo de un sistema de gestión de proveedores para Corporación Favorita, utilizando Java, Maven, Hibernate y PostgreSQL.

El sistema permite trabajar con información de proveedores y gestionar sus relaciones con productos, categorías, contactos y contratos mediante el uso de persistencia ORM con JPA.

## Tecnologías utilizadas

- **Lenguaje:** Java.
- **Gestor de dependencias:** Maven.
- **Persistencia:** Jakarta Persistence (JPA).
- **ORM:** Hibernate.
- **Base de datos:** PostgreSQL.
- **IDE:** Apache NetBeans.
- **Control de versiones:** Git y GitHub.

## Estructura del proyecto

El proyecto está organizado en paquetes siguiendo una separación de responsabilidades:

```text
com.corporacionfavorita
│
├── CRUDProveedores.java
│
├── model
│   ├── Proveedor.java
│   ├── Producto.java
│   ├── Categoria.java
│   ├── Contacto.java
│   └── Contrato.java
│
├── dao
│   ├── ProveedorDAO.java
│   └── ConsultasJPQL.java
│
├── controlador
│
├── vista
│
└── util
    └── PruebaConexion.java
```

### Descripción de los paquetes

- **model:** contiene las entidades que representan las tablas de la base de datos.
- **dao:** contiene las clases encargadas del acceso y las consultas a los datos.
- **controlador:** destinado a gestionar la lógica de control de la aplicación.
- **vista:** destinado a los componentes de la interfaz gráfica.
- **util:** contiene herramientas auxiliares, como la prueba de conexión.

## Modelo de datos y relaciones

El sistema está compuesto por cinco entidades principales:

| Entidad | Descripción |
|---|---|
| Proveedor | Almacena la información de los proveedores. |
| Producto | Contiene los productos asociados a proveedores y categorías. |
| Categoria | Clasifica los productos. |
| Contacto | Almacena los contactos de cada proveedor. |
| Contrato | Registra los contratos de los proveedores. |

### Relaciones entre entidades

- Un proveedor puede tener varios productos.
- Un proveedor puede tener varios contactos.
- Un proveedor puede tener varios contratos.
- Una categoría puede contener varios productos.

## Persistencia y consultas

El proyecto utiliza Hibernate como proveedor de persistencia JPA para gestionar las operaciones con PostgreSQL.

Se utiliza JPQL para realizar consultas sobre las entidades, incluyendo:

- Búsqueda de proveedores por nombre.
- Consulta de productos por categoría.
- Consulta de contratos activos.
- Consulta de todos los productos.
- Consulta de contactos asociados a un proveedor.
- Consultas paginadas para organizar los resultados.

## Configuración de la base de datos

Para ejecutar el proyecto se necesita PostgreSQL instalado y una base de datos llamada:

```sql
CREATE DATABASE bd_proveedores;
```

La conexión se configura en:

`src/main/resources/META-INF/persistence.xml`

Los parámetros de conexión deben ajustarse según la configuración local de PostgreSQL:

- Base de datos: `bd_proveedores`
- Usuario: el usuario configurado en PostgreSQL.
- Contraseña: la contraseña correspondiente al usuario.
- Puerto predeterminado: `5432`.

Hibernate está configurado con la propiedad `hibernate.hbm2ddl.auto` en modo `update`, por lo que puede actualizar el esquema de la base de datos según las entidades.

## Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/Darrel86/CRUDProveedores.git
```

2. Abrir el proyecto desde Apache NetBeans.

3. Verificar que Maven descargue las dependencias necesarias.

4. Crear la base de datos `bd_proveedores` en PostgreSQL.

5. Configurar las credenciales de conexión en `persistence.xml`.

6. Ejecutar **Clean and Build** para compilar el proyecto.

7. Ejecutar la clase principal `CRUDProveedores.java`.

## Diagrama UML

El repositorio incluye el diagrama UML de clases, donde se representan las entidades del sistema, sus atributos y las relaciones entre ellas.

## Autor

Darwin Ismael Torres Caguano

Proyecto académico – Gestión de Proveedores.

---

**Repositorio:** [CRUDProveedores – GitHub](https://github.com/Darrel86/CRUDProveedores)
