/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;

import java.io.*;
import java.util.Scanner;


/**
 *
 * @author diego
 */
public class Entrada {
    private int[][] tablero;
    private int filas;
    private int columnas;
    
    private boolean modoTest = false;
 
    // Parámetros del test, solo los usamos si modoTest es true
    private Test test;
    private String testFicheroCSV = "complejidad.csv";
    
    
    public void leerDatos(String[] args){
        boolean porTeclado = (args.length == 0);
 
        if(!porTeclado){
            leerDatosFichero(args[0]);
        }else{
            leerDatosTeclado();
        }
    }
    
    private void leerDatosFichero(String ruta){
        File fichero = new File(ruta);
        if(!fichero.exists()){
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FICHERO_NO_ENCONTRADO,"'" + ruta + "' no existe.");
        }
        try(Scanner sc = new Scanner(fichero)){
            leerTablero(sc, false);
        }catch(ExcepcionPropia exc){
            throw exc;
        }catch(FileNotFoundException exc){
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FICHERO_NO_ENCONTRADO, "'" + ruta + "'.", exc);
        }catch(Exception exc) {
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FORMATO_INVALIDO, exc.getMessage(), exc);
        }
    }
    
    private void leerDatosTeclado(){
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Modo Teclado ---");
        System.out.println("Escoge un modo de ejecución: ");
        System.out.println("\t 1) Introducir el laberinto manualmente");
        System.out.println("\t 2) Ejecutar test de complejidad");
        System.out.print("Opción: ");
 
        int opcion = 0;
        while(opcion<1 || opcion>2){
            if(sc.hasNextInt()){
                opcion = sc.nextInt();
            }else{
                sc.next();
            }
            if(opcion < 1 || opcion > 2){                          // Si el número no vale, o el formato era erróneo
                System.out.println("Formato no válido. Vuelva a intentarlo...");
            }
        }
        
        if(opcion == 2){
            modoTest = true;
            leerParametrosTest(sc);
        }else{
            leerTablero(sc, true);
        }
        
        // No hace falta cerrar System.in
    }
    
    private void leerTablero(Scanner sc, boolean porTeclado){
        // Dimensiones del tablero
        if(porTeclado) System.out.print("Introduzca número de filas: ");
        this.filas = leerEntero(sc, "número de filas");
        
        if(porTeclado) System.out.print("Introduzca número de columnas: ");
        this.columnas = leerEntero(sc, "número de columnas");
 
        if(filas <= 0 || columnas <= 0){
            throw new ExcepcionPropia(ExcepcionPropia.Causa.DIMENSION_INVALIDA,"Filas: " + filas + "; Columnas: " + columnas);
        }
        
        // Valores del tablero
        this.tablero = new int[filas][columnas];
        for(int i = 0; i < filas; i++){
            if(porTeclado) System.out.println("Fila " + i + ":");
            for(int j = 0; j < columnas; j++){
                int valor = leerEntero(sc, "celda [" + i + "," + j + "]");
                if(valor != 0 && valor != -1 && valor != 1) {
                    throw new ExcepcionPropia(ExcepcionPropia.Causa.VALOR_FUERA_DE_RANGO,"Celda [" + i + "," + j + "] = " + valor + "\t Solo se permite -1, 0 o 1.");
                }
                tablero[i][j] = valor;
            }
        }
    }
    private void leerParametrosTest(Scanner sc){
        System.out.println("\n--- Configuración del test ---");
 
        System.out.printf("Tamaño máximo del tablero (por defecto %d): ", Test.TAMANO_DEF);
        int testTamanoMaximo = leerEnteroOpcional(sc);
 
        System.out.printf("Intentos por tamaño (por defecto %d): ", Test.INTENTOS_DEF);
        int testIntentos = leerEnteroOpcional(sc);
 
        System.out.printf("Probabilidad de pared 0-1 (por defecto %.2f): ", Test.PROB_DEF);
        double testProbPared = leerDoubleOpcional(sc);
        
        test = new Test(testTamanoMaximo, testIntentos, testProbPared);
        System.out.printf("Nombre del fichero CSV (por defecto %s): ", testFicheroCSV);
        sc.nextLine(); // consumir salto de línea
        String linea = sc.nextLine().trim();
        if(!linea.isEmpty()) testFicheroCSV = linea;
        else GestorErrores.avisar("Se usará el nombre por defecto: " + testFicheroCSV);
    }
    
    private int leerEntero(Scanner sc, String campo){
        if(sc.hasNextInt()) return sc.nextInt();
        if(!sc.hasNext()){
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FORMATO_INVALIDO,"No se introdujo un valor para " + campo);
        }else{
            throw new ExcepcionPropia(ExcepcionPropia.Causa.FORMATO_INVALIDO,"En lugar de un entero en " + campo + ", se introdujo " + sc.next());
        }   
    }
    private int leerEnteroOpcional(Scanner sc){
        int valor = -1;
        if(sc.hasNextInt()){
            valor = sc.nextInt();
        }else{
           sc.next();
        }
        return valor;
    }
    private double leerDoubleOpcional(Scanner sc){
        double valor = -1;
        if(sc.hasNextDouble()){
            valor = sc.nextDouble();
        }else{
           sc.next();
        }
        return valor;
    }
    
    
    
   //getters
    public boolean esModoTest(){ 
        return modoTest; 
    }
    public String getTestFicheroCSV(){
        return testFicheroCSV; 
    }
    public int[][] getTablero(){
        return tablero; 
    }
    public int getFilas(){
        return filas; 
    }
    public int getColumnas(){
        return columnas; 
    }
    public Test getTest(){
        return test; 
    }
}