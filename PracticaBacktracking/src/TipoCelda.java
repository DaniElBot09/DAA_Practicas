/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;
/**
 *
 * @author DaniElBot
 */
public enum TipoCelda{
    L(0), 
    E(-1), 
    T(1);

    private final int valor;
    TipoCelda(int valor){this.valor = valor;}

    // Método para convertir de int a Enum
    public static TipoCelda desdeEntero(int id) {
        for (TipoCelda tipo : values()) {
            if(tipo.valor == id) return tipo;
        }
        return E;    // Si no encuentra el valor, le asigna paso estrecho directamente
    }
}
