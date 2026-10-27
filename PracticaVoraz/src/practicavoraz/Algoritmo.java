/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicavoraz;
import java.util.*;
/**
 *
 * @author diego
 */

public abstract class Algoritmo {
    public static ArrayList<Integer> voraz(ArrayList<Actividad> opciones){
        ArrayList <Integer> listaIndexEntrada = new ArrayList<>();
        
        Collections.sort(opciones);
        //Recogemos los índices de las posiciones en la lista de las opciones
        if(opciones.isEmpty()) return new ArrayList<>();     //Si está vacía, salimos
        
        Actividad anterior = opciones.get(0);
        listaIndexEntrada.add(anterior.getIndice());

        for(int i = 1; i<opciones.size(); i++){
            Actividad opcion = opciones.get(i);
            if(opcion.compatible(anterior)){
                listaIndexEntrada.add(opcion.getIndice());
                anterior = opcion;
            }
        }
        return listaIndexEntrada;
   }    
}