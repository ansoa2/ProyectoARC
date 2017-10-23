/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Cliente;
import Cliente.ClienteTCP;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 *
 * @author PauMiquel
 */
public class Sala {
    
    private int max_clientes;
    ArrayList<ClienteTCP> clientes;
    private int id_sala;
    private ServerSocket ss;
    private static int next_id = 0;
    
    public Sala(int max_clientes, ServerSocket serverSocket)
    {
        this.max_clientes = max_clientes;
        this.clientes = new ArrayList<>();
        this.id_sala = next_id;
        next_id++;
        this.ss = serverSocket;
    }
    
    public int getClientesSala(){
        return clientes.size();
    }

    public ArrayList<ClienteTCP> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<ClienteTCP> clientes) {
        this.clientes = clientes;
    }
    
    public void anyadirCliente(ClienteTCP cli){
        this.clientes.add(cli);
    }
    
    public void mostrarSala()
    {
        for(int i = 0; i < clientes.size(); i++)
            System.out.println("Cliente i: " + clientes.get(i));
    }
}
