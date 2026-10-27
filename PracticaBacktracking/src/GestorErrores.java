/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;
 
// Clase de gestión de errores
public class GestorErrores {
    // Error ya previsto en la aplicación
    public static void terminar(ExcepcionPropia exc) {
        System.err.println("\n");
        System.err.println("Error: " + exc.getCausa().getDescripcion());
        System.err.println("\t" + exc.getMessage());
        System.exit(1);
    }
 
    // Error no previsto en la aplicación
    public static void terminar(Exception exc) {
        System.err.println("\n");
        System.err.println("Error inesperado: " + exc.getClass().getSimpleName());
        System.err.println("\t" + exc.getMessage());
        System.exit(1);
    }
 
    // Avisos que no necesariamente son por un error
    public static void avisar(String mensaje) {
        System.err.println("Aviso: " + mensaje);
    }
}