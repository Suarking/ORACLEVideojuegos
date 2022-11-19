/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oraclevideojuegos.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oraclevideojuegos.services.DAOController;

/**
 *
 * @author fn7se
 */
public class App {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int opcion;
        boolean salir = false;

        System.out.println("******************************************");
        System.out.println("***ORACLE**CRUD***VIDEOGAMES******by*SPL**");
        System.out.println("******************************************");

        do {
            try {
                System.out.println("1- CREAR TABLA");
                System.out.println("2- VER LISTA DE JUEGOS");
                System.out.println("3- INTRODUCIR NUEVO JUEGO");
                System.out.println("4- MODIFICAR JUEGO (SIMULACRO DE STOCK)");
                System.out.println("5- ELIMINAR JUEGO");
                System.out.println("6- BORRAR TODO (SECUENCIA Y TABLA)");
                System.out.println("7- SALIR");
                System.out.println("\n Selecciona una de las opciones anteriores");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        DAOController dAOController = new DAOController();
                        dAOController.createTable();

                        break;
                    case 2:
                        DAOController dAOController2 = new DAOController();
                        dAOController2.showGames();
                        break;
                    case 3:
                        insertMenu();
                        break;
                    case 4:
                        
                        DAOController dAOController3 = new DAOController();
                        dAOController3.showStock();
                        modMenu();
                        dAOController3.showStock();

                        break;
                    case 5:
                        deleteMenu();

                        break;
                    case 6:
                        DAOController dAOController4 = new DAOController();
                        dAOController4.deleteAll();

                        break;
                                            case 7:
                        salir = true;

                        break;
                    default:
                        throw new AssertionError();
                }
            } catch (SQLException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (!salir);

    }

    public static void deleteMenu() {
        try {
            DAOController dAOController = new DAOController();
            dAOController.showGames();

            System.out.println("Introduce el ID del juego a eliminar");
            int iddelete = sc.nextInt();
            dAOController.deleteGameByID(iddelete);

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void insertMenu() {
        try {
            String nombre;
            String descripcion;
            String plataforma;
            double precio;
            int cantidad;

            sc.nextLine();
            DAOController dAOController = new DAOController();
            System.out.println("Introduce el nombre del juego: ");
            nombre = sc.nextLine();
            System.out.println("Introduce la descripción del juego: ");
            descripcion = sc.nextLine();
            System.out.println("Introduce la plataforma: ");
            plataforma = sc.nextLine();
            System.out.println("Introduce el precio del juego: ");
            precio = sc.nextDouble();
            System.out.println("Introduce el stock del juego: ");
            cantidad = sc.nextInt();

            dAOController.insertGame(nombre, descripcion, plataforma, precio, cantidad);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputMismatchException ex) {
            System.out.println("Tipo de dato introducido no válido");
        }

    }

    public static void modMenu() {
        try {
            int idmod;
            int pedidos;

            sc.nextLine();
            DAOController dAOController = new DAOController();
            System.out.println("Simulador de cambio de stock por pedido, modifica el stock a 50");
            System.out.println("Introduce la ID del juego a actualizar stock: ");
            idmod = sc.nextInt();

            dAOController.modStock(idmod);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (InputMismatchException ex) {
            System.out.println("Tipo de dato introducido no válido");
        }

    }
}
