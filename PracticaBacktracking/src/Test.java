/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author diego
 */
public class Test {
    public final static int TAMANO_DEF = 30;
    public final static int INTENTOS_DEF = 50;
    public final static double PROB_DEF = 0.15;
    private int tamanoMax = TAMANO_DEF;
    private int intentosPorTamano = INTENTOS_DEF;
    private double probabilidadPared =  PROB_DEF;
    
    public Test(int tamano, int intentosPorTamano, double probabilidadPared){
        if(tamano <= 0){
            GestorErrores.avisar("Se usará el valor por defecto para tamaño, ya que se salió de rango: " + TAMANO_DEF);
        }else{
            tamanoMax = tamano;
        }
        
        if(intentosPorTamano <= 0){
            GestorErrores.avisar("Se usará el valor por defecto para 'intentos', ya que se salió de rango: " + INTENTOS_DEF);
        }else{
            this.intentosPorTamano = intentosPorTamano;
        }
        
        if(probabilidadPared < 0 || probabilidadPared > 1){
            GestorErrores.avisar("Se usará el valor por defecto para 'probabilidad', ya que se salió de rango: " + PROB_DEF);
        }else{
            this.probabilidadPared = probabilidadPared;
        }
    }
    
    private int[][] generarTableroAzar(int tam) {
        int[][] tablero = new int[tam][tam];

        for(int f = 0; f < tam; f++){
            for (int c = 0; c < tam; c++) {
                if(Math.random() < probabilidadPared){
                    tablero[f][c] = -1;
                }else{
                    tablero[f][c] = 0;
                }
            }
        }
        tablero[0][0] = 0;

        int fTornillo = (int)(Math.random() * tam);
        int cTornillo = (int)(Math.random() * tam);

        tablero[fTornillo][cTornillo] = 1;

        return tablero;
    }


    public void complejidad(){
        System.out.println("N (Filas)\tCeldas Totales\tTiempo Medio (ms)\tNodos (Media)");
        System.out.println("----------------------------------------------------------------------");

        for (int i = 1; i <= tamanoMax; i++) {
            long acumuladorNanos = 0;
            long acumuladorPasos = 0;

            for (int r = 0; r < intentosPorTamano; r++) {
                int[][] tablero = generarTableroAzar(i);
                Robot robot = new Robot(tablero);

                long inicio = System.nanoTime();
                robot.buscarTornillo();
                long fin = System.nanoTime();

                acumuladorNanos += (fin - inicio);
                acumuladorPasos += robot.getContadorPasos();
            }

            double tiempoMedioMs = (acumuladorNanos / (double) intentosPorTamano) / 1_000_000.0;
            double pasosMedios = (double) acumuladorPasos / intentosPorTamano;

            System.out.printf("%d \t\t %d \t\t %.4f \t\t %.1f%n", i, (i * i), tiempoMedioMs, pasosMedios);
        }
    }
    public void exportarCSV(String nombreFichero){
        File archivo = new File(nombreFichero);
        if (archivo.exists()) {
            System.err.println("Error: El fichero de salida '" + nombreFichero + "' ya existe.");
            return;
        }
 
        System.out.println("\n Ejecutando test de complejidad...");
        System.out.printf("Tamaño máximo: " + tamanoMax);
        System.out.printf("Intentos/tamaño: " + intentosPorTamano);
        System.out.printf("Prob. pared: %.2f%n%n", probabilidadPared);
 
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            bw.write("N; Celdas Totales; Tiempo Medio (ms); Pasos Media");
            bw.newLine();
 
            for(int i = 1; i <= tamanoMax; i++) {
                long acumuladorNanos = 0;
                long acumuladorPasos = 0;
 
                for (int r = 0; r < intentosPorTamano; r++) {
                    int[][] tablero = generarTableroAzar(i);
                    Robot robot = new Robot(tablero);
 
                    long inicio = System.nanoTime();
                    robot.buscarTornillo();
                    long fin = System.nanoTime();
 
                    acumuladorNanos += (fin - inicio);
                    acumuladorPasos += robot.getContadorPasos();
                }
 
                double tiempoMedioMs = (acumuladorNanos / (double) intentosPorTamano) / 1_000_000.0;
                double pasosMedios   = (double) acumuladorPasos / intentosPorTamano;
 
                bw.write(String.format("%d;%d;%.6f;%.2f", i, (i * i), tiempoMedioMs, pasosMedios));
                bw.newLine();
 
                System.out.printf("  N=%2d  |  %.4f ms  |  %.1f Pasos%n", i, tiempoMedioMs, pasosMedios);
            }
 
            System.out.println("\nCSV guardado en: " + archivo.getAbsolutePath());
 
        }catch(IOException e){
            System.err.println("Error al escribir el CSV: " + e.getMessage());
        }
    }
}