/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;

/**
 *
 * @author diego
 */
public class Mapa {
    private final TipoCelda[][] tablero;
    private final boolean[][] explorados;         // Como dibujar la línea en un laberinto
    
    public Mapa(int[][] datos){
        int filas = datos.length;
        int columnas = datos[0].length;
        
        this.tablero = new TipoCelda[filas][columnas];
        this.explorados = new boolean[filas][columnas];

        // Convertimos la matriz de enteros a L,E,T
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                this.tablero[i][j] = TipoCelda.desdeEntero(datos[i][j]);
            }
        }
    }
    
    public boolean dimCorrectas(int f, int c){              //Dimensiones correctas
        return (0<=f) && (f<tablero.length) && (0<=c) && (c<tablero[0].length);
    }
    public boolean transitable(int f, int c){
        return dimCorrectas(f,c) && !(tablero[f][c] == TipoCelda.E);    // Dimensiones correctas y pared no estrecha
    }
    public boolean inexplorado(int f, int c){
        return dimCorrectas(f,c) && !explorados[f][c];       // Comprueba que no hemos explorado esa casilla todavía
    }
    public void marcar(int f, int c){
        if(transitable(f,c)){
            explorados[f][c] = true;             // Si puede mover a esa casilla, la pone en true
        }
    }
    public boolean hayTornillo(int f, int c){
        if(!dimCorrectas(f,c)){
            return false;             // Si está fuera de límites, devuelve false
        }
        return tablero[f][c] == TipoCelda.T;
    }
    public void deshacer(int[] pos){     // No lo vamos a usar
        explorados[pos[0]][pos[1]] = false;
    }
}