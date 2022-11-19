/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oraclevideojuegos.services;

import java.math.BigDecimal;
import java.sql.*;
import oraclevideojuegos.util.OrcConnection;

/**
 *
 * @author fn7se
 */
public class DAOController {

    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    String sql;

    public void createTable() throws SQLException {
        try {
            System.out.println("Conectando...");
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);

            stmt = connection.createStatement();
            // Creamos secuencia para el auto increment de ID
            sql = """
                              CREATE SEQUENCE id_sequence\r
                                START WITH 1\r
                                INCREMENT BY 1\r
                                NOCACHE""";
            stmt.execute(sql);
            //Creamos el tipo de objeto en la tabla
            sql = "CREATE Or Replace TYPE VideojuegoType AS OBJECT (id NUMBER,nombre VARCHAR2(25),descripcion VARCHAR2(22),plataforma VARCHAR2(10),precio NUMBER(5, 2));";
            stmt.execute(sql);
            //Creamos la tabla e introducimos el objeto
            sql = """
                              CREATE TABLE videojuegos (\r
                              vid VideojuegoType,\r
                              cantidad NUMBER\r
                              )""";
            stmt.execute(sql);
            //Insertamos algunos videojuegos
            sql = """
                              INSERT INTO videojuegos\r
                              VALUES (VideojuegoType(id_sequence.nextval,'God Of War: Ragnarok', 'Juego tipo HackSlash', 'PS5', 80),40)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos \r
                              VALUES (VideojuegoType(id_sequence.nextval,'A Plague Tale: Requiem', 'Juego tipo Aventura', 'XBSX', 75),12)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos \r
                              VALUES (VideojuegoType(id_sequence.nextval,'Fifa 2023', 'Juego tipo Deportivo', 'PS5', 77),11)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos \r
                              VALUES (VideojuegoType(id_sequence.nextval,'Pok\u00e9mon Escarlata', 'Juego tipo RPG', 'SWITCH', 70),61)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos\r
                              VALUES (VideojuegoType(id_sequence.nextval,'The Callisto Protocol', 'Juego tipo Terror', 'PS5', 10),10)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos\r
                              VALUES (VideojuegoType(id_sequence.nextval,'Do not Open', 'Juego tipo Terror', 'PC', 53),33)""";
            stmt.execute(sql);
            sql = """
                              INSERT INTO videojuegos\r
                              VALUES (VideojuegoType(id_sequence.nextval,'Lets Sing 2023', 'Juego tipo Otros', 'SWITCH', 10),10)""";
            stmt.execute(sql);
            connection.commit();

            System.out.println("Tabla videojuegos creada, algunos videojuegos en stock fueron añadidos");

        } catch (SQLException e) {
            System.out.println("Imposible realizar la operación, revirtiendo cambios");
            connection.rollback();
            // TODO: handle exception
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar la conexión");
        }

    }

    public void insertGame(String nombre, String descripcion, String plataforma, double precio, int cantidad) throws SQLException {
        try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);

            System.out.println("Conexion establecida");
            stmt = connection.createStatement();
            sql = """
                  INSERT INTO videojuegos\r
                  VALUES (VideojuegoType(id_sequence.nextval,'""" + nombre + "', '"
                    + descripcion + "', '" + plataforma + "', " + precio + ")," + cantidad + ")";
            stmt.execute(sql);
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }
    }

    public void showGames() throws SQLException {
        try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            sql = "SELECT v.vid from videojuegos v order by v.vid.id asc";
            rs = stmt.executeQuery(sql);
            connection.commit();
            while (rs.next()) {
                Struct videoJ = (Struct) rs.getObject(1);
                Object[] elements = videoJ.getAttributes();
                Integer id = ((BigDecimal) elements[0]).intValue();
                String nombre = (String) elements[1];
                String descripcion = (String) elements[2];
                String plataforma = (String) elements[3];
                Double precio = ((BigDecimal) elements[4]).doubleValue();

                System.out.println("ID: " + id);

                System.out.println("Nombre: " + nombre);
                System.out.println("Descripción: " + descripcion);
                System.out.println("Plataforma: " + plataforma);
                System.out.println("Precio: " + precio.toString() + "€\n");
            }
        } catch (SQLException e) {
            //Si hay error, rollback
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }

    }

    public void showStock() throws SQLException {
        try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);
            stmt = connection.createStatement();

            sql = "select V.vid.id, V.vid.nombre, V.cantidad from videojuegos V order by cantidad desc";
            rs = stmt.executeQuery(sql);
            connection.commit();
            System.out.println("******STOCK**ACTUAL**ORDENADO**POR**CANTIDAD******\n");
            while (rs.next()) {
                System.out.print("ID: " + rs.getString("vid.id"));
                System.out.print("    Nombre: " + rs.getString("vid.nombre"));
                System.out.println("   Stock: " + rs.getInt("cantidad"));
            }

        } catch (SQLException e) {
            //Si hay error, rollback
            System.out.println("Error, realizando rollback");
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }
    }
    
    public void modStock(int idmod) throws SQLException{
    try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);
            sql = "UPDATE VIDEOJUEGOS V SET V.CANTIDAD=50 WHERE V.vid.id="+idmod;
            stmt = connection.createStatement();

            
            stmt.execute(sql);
            connection.commit();
            
            System.out.println("Pedido recibido del juego con ID " + idmod + ", stock establecido en 50");

        } catch (SQLException e) {
            //Si hay error, rollback
            System.out.println("Error en la operación, realizando rollback");
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }
    }

    public void deleteGameByID(int iddelete) throws SQLException {
        try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);

            System.out.println("Conexion establecida");
            stmt = connection.createStatement();
            sql = "delete from videojuegos v where v.vid.id like '" + iddelete + "'";
            stmt.execute(sql);
            connection.commit();
            System.out.println("Juego con ID " + iddelete + " borrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error en la operación de borrado");
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }
    }
    
    public void  deleteAll() throws SQLException{
        try {
            OrcConnection oracleConn = new OrcConnection();
            connection = oracleConn.Connect();
            //Autocommit false para gestionar transacciones
            connection.setAutoCommit(false);
            stmt = connection.createStatement(); 
            sql = "drop sequence id_sequence";           
            stmt.execute(sql);
            sql = "drop table videojuegos";           
            stmt.execute(sql);
            
            connection.commit();
            
            System.out.println("Secuencia y tabla videojuegos borradas corerctamente");

        } catch (SQLException e) {
            //Si hay error, rollback
            System.out.println("Error en la operación, realizando rollback");
            connection.rollback();
        }
        try {
            stmt.close();
            connection.close();
        } catch (SQLException e) {
        }
    }
}
