/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package practicavoraz;
import java.util.*;
import java.io.*;
/**
 *
 * @author diego
 */
public class PracticaVoraz {

    public static void main(String[] args) throws IOException, Exception{
        ArrayList<Actividad> tablon = new ArrayList<>();
        Scanner sc = null;
        boolean porTeclado = !(args.length > 0);
        try{
            if(!porTeclado){        // Desde un fichero
                File fichero = new File(args[0]);
                sc = new Scanner(fichero);
            }else{                      // Desde el teclado
                System.out.println("Introduzca el numero de tareas: ");
                sc = new Scanner(System.in);
            }
            if(sc.hasNextInt()){
                int n = sc.nextInt();   // Número de tareas
                if(n<=0){
                    throw new IllegalArgumentException("El número de tareas debe ser mayor que 0. ");
                }
                for(int i = 0; i < n; i++){
                    if(porTeclado){
                        System.out.println("Tarea número... " + i); 
                        System.out.print("Inicio: "); 
                    }
                    if(!sc.hasNextInt()){
                        throw new IllegalArgumentException("Se esperaba un entero como inicio. ");
                    }
                    int inicio = sc.nextInt();
                    if(porTeclado) System.out.print("Fin: ");
                    if(!sc.hasNextInt()){
                        throw new IllegalArgumentException("Se esperaba un entero como final. ");
                    }
                    int fin = sc.nextInt();
                    tablon.add(new Actividad(inicio, fin));
                }
            }else{
                throw new IllegalArgumentException("Debe introducir el número (entero) de tareas. ");
            }
            
            //Resolvemos el problema planteado
            ArrayList<Integer> sol = Algoritmo.voraz(tablon);
            
            // Escribimos resultados
            if(args.length > 1){
                File ficheroSalida = new File(args[1]);
                if(ficheroSalida.exists()){
                    System.err.println("Error: El fichero de salida '" + args[1] + "' ya existe.");
                    return; 
                }
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida))) {
                    bw.write("Tareas seleccionadas: " + sol);
                    bw.newLine();
                }
            }else{
                System.out.println("Tareas seleccionadas: " + sol);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error encontrando el fichero de lectura: " + e.getMessage());
        } catch(InputMismatchException e) {
            System.err.println("Error: formato de número incorrecto.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        } finally {
            if (!porTeclado && sc != null) sc.close(); 
        }
    }
}
