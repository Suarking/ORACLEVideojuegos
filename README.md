# PRÁCTICA4 VIDEOjuegos SUAR PL

_Práctica de Acceso a datos correspondiente a la Unidad 4_

## Resumen del proyecto 🚀

_El proyecto simula la gestión de un inventario de videojuegos, desarrollado en Oracle y en el que se realizan operaciones CRUD en tablas orientadas a objetos._

En el pdf adjunto se detalla la estructura y el funcionamiento de sus métodos, además de disponer de un enlace a un vídeo demostrativo.

### Pre-requisitos 📋

_Antes de poner en marcha el proyecto, necesitaremos:_

_ -JAVA JRE funcional, en el ejemplo se usa Java 8, JRE1.8._
_ -Servidor Oracle funcional._
_ Oracle SQL Developer o similar._

_El script de Oracle que se ha utilizado para crear el usuario y otorgarle permisos es el siguiente :_


```
alter session set "_ORACLE_SCRIPT"=true;
create user ADMIN identified by contrasena;
grant all privileges to ADMIN;
```

_Las tablas, objetos y secuencia se realizan desde el mismo programa_

## Ejecutando el programa ⚙️

_Los pasos están descritos con más detalle en el pdf adjunto y en el vídeo demostrativo:_
_Configuramos la conexión local con los datos facilitados en el pdf adjunto a la entrega.
_Creamos el usuario en SQL Developer con el script anterior._
_Importamos el proyecto adjunto, preferentemente en Netbeans o en tu IDE Java._
_Agregamos el archivo del conector  jar adjunto a las librerías_
_Ejecutamos el proyecto desde el IDE y manejamos el menú mostrado en consola introduciendo los valores solicitados._


## Construido con 🛠️

_Las herramientas utilizadas para la creación del proyecto son:_

* [Netbeans](https://netbeans.apache.org/download/index.html/) - IDE Java, versión 15
* [SQL Developer](https://www.oracle.com/database/sqldeveloper/technologies/download/) - Gestor de DBB, versión 22.2.1.234

## Link al proyecto en Github ✒️

* [Práctica 4](https://github.com/Suarking/ORACLEVideojuegos.git)

## Autores ✒️

_Proyecto realizado por:_

* **Suar PL**



---
