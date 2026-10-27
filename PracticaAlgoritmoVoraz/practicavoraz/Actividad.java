/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicavoraz;

/**
 *
 * @author DaniElBot
 */
public class Actividad implements Comparable<Actividad>{
    //Atributos
    private static int i = 0;
    final private int indice;
    private int inicio;
    private int fin;
    //Constructor
    public Actividad(int inicio, int fin){
        indice = i;
        if (inicio < 0 || fin < 0 || fin <= inicio) {
            throw new IllegalArgumentException("Intervalo inválido: [" + inicio + ", " + fin + "]");
        }
        this.inicio = inicio;
        this.fin = fin;
        
        i++;
    }
    
    //Métodos
    public int getInicio(){
        return inicio;
    }
    public int getFin(){
        return fin;
    }
    public int getIndice(){
        return indice;
    }

    @Override
    public int compareTo(Actividad otra) {
        return Integer.compare(this.fin, otra.fin); 
    }
    public boolean compatible(Actividad otra){
        return this.fin <= otra.inicio || otra.fin <= this.inicio;
    }
}
