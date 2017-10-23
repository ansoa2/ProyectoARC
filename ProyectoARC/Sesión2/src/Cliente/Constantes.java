/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

/**
 *
 * @author PauMiquel
 */
public interface Constantes {
    // IP del servidor
    public static final String IP_SERVIDOR = "localhost";
    
    // Puerto del servidor
    public static final int PUERTO_SERVIDOR = 2017;
    
    // Tamaño del mensaje
    public static final int TAM_MENSAJE = 1024;
    
    // Separador de STRINGTOKEN en el mensaje
    public static final String SEPARADOR = "$";
    
    // Tiempo repeticion de TimeTask en milisegundos
    public static final int TIEMPO_REPETICION = 200;
    
    // Mensaje final
    public static final String MENSAJE_FINAL = "FINAL";
    
    public static final String MENSAJE_INICIAL = "INICIAL";
    
    public static final String ANSI_RED = "\u001B[31m";
}
