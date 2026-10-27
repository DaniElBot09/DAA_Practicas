/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package robottornillo;
import java.util.Stack;

/**
 *
 * @author diego
 */
public class RobotTornillo {
 
    public static void main(String[] args) {
        Entrada entrada = new Entrada();
        Salida  salida  = new Salida();
 
        try{
            entrada.leerDatos(args);
 
            if(entrada.esModoTest()){
                // Si el usuario quiere hacer un test de complejidad, la entrada recibe los parámetros, lo construye, y test genera el CSV
                entrada.getTest().exportarCSV(entrada.getTestFicheroCSV());
            }else{
                // Camino normal del algoritmo. Se crea el robot, se le dan los datos de la entrada, y crea el camino
                Robot robot = new Robot(entrada.getTablero());
                Stack<int[]> camino = robot.buscarTornillo();
                salida.mostrarResultados(camino, args);
            }
        }catch(ExcepcionPropia e){
            GestorErrores.terminar(e);          // error conocido
        }catch(Exception e){
            GestorErrores.terminar(e);          // error no contemplado
        }
    }
}