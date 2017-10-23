/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import Servidor.Servidor;
import java.util.ArrayList;

/**
 *
 * @author PauMiquel
 */
public class Cliente {
    
    private int num_clientes;
    ArrayList<ClienteTCP> clientes;
    ClienteTCP cl;
    public Cliente() throws IOException {
        this.num_clientes = leerInt();
        this.clientes = new ArrayList<>();        
            
        for(int i = 0; i < num_clientes; i++){
                cl = new ClienteTCP();
                clientes.add(cl);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Cliente client = new Cliente();
    }

    public int leerInt() {
        int x = 0;
        boolean valido = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduzca el número de clientes: ");
            try {
                x = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("ERROR al introducir datos, sólo se aceptan números enteros.");
            }
        } while (!valido);
        return x;
    }

}
