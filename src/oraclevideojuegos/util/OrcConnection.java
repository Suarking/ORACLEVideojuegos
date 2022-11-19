/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oraclevideojuegos.util;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fn7se
 */
public class OrcConnection {

    String user = "ADMIN";
    ;
	String pass = "contrasena";
    String url = "jdbc:oracle:thin:@localhost:1521:xe";
    Connection connection;

    public Connection Connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión realizada");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println("Conexión no realizada");
        }
        return connection;
    }
}
