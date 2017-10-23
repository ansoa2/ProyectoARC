/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import static Servidor.Constantes.*;

/**
 *
 * @author PauMiquel
 */
public class Servidor {

    private int num_clientes_sala;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor();
    }

    public Servidor() throws IOException {
        this.num_clientes_sala = leerInt();
        
        ServidorTCP serverTCP = new ServidorTCP(num_clientes_sala);
    }


    public int leerInt() {
        int x = 0;
        boolean valido = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca un número de clientes por sala (rango[5...15]):");
            try {
                x = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("ERROR al introducir datos, sólo se aceptan números enteros.");
            }
        } while (!valido || x > TAM_MAX || x < TAM_MIN);
        return x;
    }
}
