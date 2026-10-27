package robottornillo;

import java.util.Stack;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author diego
 */
public class Salida {
    public void mostrarResultados(Stack<int[]> camino, String[] args){
        if(camino == null){
            System.out.println("No se ha encontrado un camino válido al tornillo.");
            return;
        }
        if(args.length > 1){
            escribirFichero(camino, args[1]);
        }else{
            escribirPantalla(camino);
        }
    }
    
    private void escribirPantalla(Stack<int[]> camino) {
        System.out.println("Camino (Tornillo → Origen):");
        for(int i = camino.size() - 1; i >= 0; i--){   // Recorremos la pila sin sacar nada (por si acaso)
            int[] pos = camino.get(i);
            System.out.println("[" + pos[0] + "," + pos[1] + "]");
        }
    }
    
    private void escribirFichero(Stack<int[]> camino, String nombreFichero) {
        File archivo = new File(nombreFichero);
        if (archivo.exists()) {
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FICHERO_YA_EXISTE,"'" + nombreFichero + "' ya existe. Elige otro nombre, o bórralo.");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            for (int i = camino.size() - 1; i >= 0; i--) {
                int[] pos = camino.get(i);
                bw.write("[" + pos[0] + "," + pos[1] + "]");
                bw.newLine();
            }
            System.out.println("Resultado guardado en " + nombreFichero);
        }catch(ExcepcionPropia exc){
            throw exc;
        }catch(IOException exc){
            throw new ExcepcionPropia(ExcepcionPropia.Causa.ERROR_ESCRITURA, "'" + nombreFichero + "': " + exc.getMessage(), exc);
        }
    }
}
      