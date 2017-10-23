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
public class TiempoCiclo {

    private long totalTiempo;
    private long tiempoInicio;

    public void iniciarTiempoCiclo() {
        // Inicializa con el tiempo actual
        tiempoInicio = System.currentTimeMillis();
        //tiempoInicio = System.nanoTime();
    }

    public void finalizaTiempoCiclo() {
        // Resta el tiempo de inicio al actual resultando el tiempo usado
        totalTiempo = System.currentTimeMillis() - tiempoInicio;
        //totalTiempo = System.nanoTime()- tiempoInicio;
    }

    @Override
    public String toString() {
        return "Tiempo ciclo del menasje:\t" + totalTiempo + " milisegudos.";
    }
}
