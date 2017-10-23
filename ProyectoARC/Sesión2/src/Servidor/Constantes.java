/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author PauMiquel
 */
public interface Constantes {

    // Puerto del servidor
    public static final int PUERTO_SERVIDOR = 2017;
    
    public static final int TAM_MAX = 15;
    
    public static final int TAM_MIN = 5;
    
    // Tamaño del mensaje
    public static final int TAM_MENSAJE = 1024;
    
    // Separador de STRINGTOKEN en el mensaje
    public static final String SEPARADOR = "$";
 
    // Mensaje final
    public static final String MENSAJE_FINAL = "FINAL";
    
    // Mensaje final
    public static final String MENSAJE_INICIAL = "INICIAL";
    
    public static final String ANSI_RED = "\u001B[31m";
    
    public static final String ANSI_BLUE = "\033[34m";
    
    public static final String ANSI_GREEN = "\u001B[32m";
    
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    
    public static final String ANSI_RESET = "\u001B[0m";
}