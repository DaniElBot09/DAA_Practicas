/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robottornillo;

/**
 *
 * @author DaniElBot
 */
// Creamos excepciones propias, que incluyen la causa del error dentro del programa
public class ExcepcionPropia extends RuntimeException{
    // Enumeración para cada tipo de error que tenemos
    public enum Causa {
        FICHERO_NO_ENCONTRADO("Fichero no encontrado"),
        FICHERO_YA_EXISTE("El fichero de salida ya existe"),
        ERROR_ESCRITURA("Error al escribir en el fichero"),
        FORMATO_INVALIDO("Formato de entrada inválido"),
        VALOR_FUERA_DE_RANGO("Valor fuera del rango permitido"),
        DATOS_INSUFICIENTES("Faltan datos en la entrada"),
        DIMENSION_INVALIDA("Las dimensiones deben ser mayores que 0");
 
        private final String descripcion;
        Causa(String descripcion){this.descripcion = descripcion;}
        public String getDescripcion(){return descripcion;}
    }
 
    private final Causa causa;
 
    public ExcepcionPropia(Causa causa, String detalle){
        super(detalle);
        this.causa = causa;
    }
 
    public ExcepcionPropia(Causa causa, String detalle, Throwable origen){
        super(detalle, origen);
        this.causa = causa;
    }
 
    public Causa getCausa() { return causa; }
}