# CRUD de Proveedores

## Descripción del proyecto

Este proyecto implementa un CRUD (Crear, Leer, Actualizar y Eliminar) de proveedores para una aplicación de ejemplo de Corporación Favorita. Está desarrollado en Java y utiliza JPA e Hibernate para la persistencia de datos en una base de datos PostgreSQL.

El sistema permite administrar la información de los proveedores mediante un menú interactivo en consola.

## Tecnologías utilizadas

- **Java:** lenguaje de programación.
- **Maven:** gestión de dependencias y configuración del proyecto.
- **JPA:** especificación para la persistencia de datos.
- **Hibernate:** implementación de JPA.
- **PostgreSQL:** sistema gestor de base de datos.
- **NetBeans IDE:** entorno de desarrollo.
- **StarUML:** elaboración del diagrama de clases.
- **Git y GitHub:** control de versiones y almacenamiento del proyecto.

## Funcionalidades

El sistema permite:

1. Registrar nuevos proveedores.
2. Listar los proveedores registrados.
3. Buscar un proveedor por su identificador.
4. Actualizar la información de un proveedor.
5. Eliminar proveedores por su identificador.
6. Finalizar la ejecución del programa.

## Estructura del proyecto

```text
CRUDProveedores/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── corporacionfavorita/
│       │           ├── CRUDProveedores.java
│       │           ├── Proveedor.java
│       │           ├── ProveedorDAO.java
│       │           └── PruebaConexion.java
│       └── resources/
│           └── META-INF/
│               └── persistence.xml
├── Diagrama_CRUD_Proveedores.mdj
├── pom.xml
├── .gitignore
└── README.md
```

## Requisitos previos

Para ejecutar el proyecto se necesita:

- Tener instalado un JDK compatible con la configuración de Maven.
- Tener instalado Apache Maven, o utilizar la integración de Maven de NetBeans.
- Tener instalado PostgreSQL.
- Tener instalado NetBeans IDE o un entorno compatible con proyectos Maven.

## Configuración de la base de datos

1. Iniciar PostgreSQL.
2. Crear una base de datos con el nombre `bd_proveedores`.
3. Abrir el archivo `src/main/resources/META-INF/persistence.xml`.
4. Verificar que la URL de conexión corresponda a la base de datos local:

   `jdbc:postgresql://localhost:5432/bd_proveedores`

5. Configurar el usuario y la contraseña de PostgreSQL en el entorno local.
6. Verificar que las credenciales coincidan con las configuradas en PostgreSQL.

Hibernate está configurado para actualizar el esquema de la base de datos según las entidades del proyecto.

**Importante:** no publicar contraseñas reales ni otras credenciales en el repositorio.

## Ejecución del proyecto

1. Abrir NetBeans IDE.
2. Seleccionar **File → Open Project**.
3. Elegir la carpeta `CRUDProveedores`.
4. Esperar a que Maven descargue las dependencias necesarias.
5. Verificar la configuración de la conexión a PostgreSQL.
6. Ejecutar la clase `CRUDProveedores.java`.
7. Utilizar las opciones del menú de consola para administrar los proveedores.

La clase `PruebaConexion.java` permite comprobar la conexión con la base de datos.

## Diagrama de clases

El archivo `Diagrama_CRUD_Proveedores.mdj` contiene el diagrama de clases elaborado con StarUML para representar la estructura principal del sistema y las relaciones entre sus clases.

## Autor

Darrel86

## Repositorio

https://github.com/Darrel86/CRUDProveedores
