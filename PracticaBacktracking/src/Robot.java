/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;

import java.util.Stack;

/**
 *
 * @author diego
 */
public class Robot{
    private long contadorPasos;
    
    public static final int[][] MOVIMIENTOS = {{1,0},{0,-1},{-1,0},{0,1}};
    public static final int[] INICIO = {0,0};
    
    private Mapa mapa;
    private Stack<int[]> camino;
    
    public Robot(int[][] tablero){
        this.mapa = new Mapa(tablero);
        this.camino = new Stack<>();
        this.contadorPasos=0;
    }
    public Robot(Mapa mapa){
        this.mapa = mapa;
        this.camino = new Stack<>();
        this.contadorPasos=0;
    }
    
    public long getContadorPasos() {
        return contadorPasos;
    }
    
    
    
    private boolean intentarMoverA(int f, int c){  // Si se puede, mover a esa casilla en el mapa, y añadir a camino
        boolean ans = mapa.transitable(f, c) && mapa.inexplorado(f,c);
        if(ans){
            mapa.marcar(f, c);               // Marca en el mapa
            camino.push(new int[]{f,c});    // Añade al camino
            contadorPasos++;
         }
         return ans;
    }
    
    public Stack<int[]> buscarTornillo(){
        //Transitamos a la posición inicial (si se puede)
        int fIni = INICIO[0], cIni = INICIO[1];
        if(!intentarMoverA(fIni,cIni)){    // Intenta moverse y, si no puede, lanza un mensaje
            System.out.println("Problema: el robot quedó atascado en la primera casilla, que era muy estrecha");
            return camino;       //Devuelve el camino vacío
        }
        // Llamamos al backtracking
        boolean encontrado = buscarTornilloBT();
        
        // Devuelve el resultado
        if(encontrado){
            return camino;
        }else{
            return null;
        }        
    }
    
    private boolean buscarTornilloBT(){   // Backtracking
        int[] actual = camino.peek();
        int f = actual[0];
        int c = actual[1];
        
        if(mapa.hayTornillo(f, c)){    // Si hay un tornillo en la posición actual, hemos ganado
            return true;
        }
        // Si encontramos el tornillo en el recorrido en profundidad, acabamos el for, para no seguir buscando
        boolean encontrado = false;
        for(int i = 0; i < MOVIMIENTOS.length && !encontrado; i++){
            int fNueva = f + MOVIMIENTOS[i][0];
            int cNueva = c + MOVIMIENTOS[i][1];
            if(intentarMoverA(fNueva, cNueva)){
                encontrado = buscarTornilloBT();     // Si lo encontramos, se parará el bucle en el siguiente
                // Si no lo encontramos, deshacemos el camino
                if(!encontrado){
                    camino.pop();
                }
            }
        }
        // Si lo hemos encontrado en la recursión, lo devolvemos
        return encontrado;
    }
}