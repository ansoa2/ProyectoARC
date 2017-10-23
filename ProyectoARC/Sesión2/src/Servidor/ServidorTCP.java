/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Cliente.Cliente;
import Cliente.ClienteTCP;
import Cliente.Localizacion;
import java.net.ServerSocket;
import java.util.ArrayList;
import static Servidor.Constantes.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PauMiquel
 */
public class ServidorTCP {

    ArrayList<Sala> salas;
    ArrayList<Cliente> clientes;
    int max_clientes_por_sala;
    private ServerSocket serverSocket;
    private Socket socketConectado;
    private DataInputStream mensajeRecibido;
    private DataOutputStream mensajeEnviado;
    Localizacion localizacion;
    ClienteTCP cliente;
    int id_cliente;
    int i = 0;
    boolean asignado = false;

    public ServidorTCP(int max_clientes_sala) {
        this.salas = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.max_clientes_por_sala = max_clientes_sala;
        this.socketConectado = null;

        Sala salaPrincipal = new Sala(max_clientes_sala, serverSocket);
        this.salas.add(salaPrincipal);

        try {
            serverSocket = new ServerSocket(PUERTO_SERVIDOR);
        } catch (IOException se) {
            System.out.println("ERROR creando el socket");
            System.err.println(se);
        }

        System.out.println("--- SERVIDOR EN ESCUCHA ---");

        while (true) {
            try {
                socketConectado = serverSocket.accept();
                //salaPrincipal.anyadirCliente(socketConectado);
                mensajeRecibido = new DataInputStream(socketConectado.getInputStream());
                mensajeEnviado = new DataOutputStream(socketConectado.getOutputStream());
                System.out.println(ANSI_RED + "Se ha conectado " + socketConectado.toString());
            } catch (IOException e) {
                System.out.println("No puede establer canales de E/S para la conexión");
                System.err.println(e);
            }

            try {
                // Leer datos del Cliente
                String recepcionDatos = mensajeRecibido.readUTF();

                for (int j = 0; j < recepcionDatos.length(); j++) {
                    if (recepcionDatos.charAt(j) == '$') {
                        id_cliente = Integer.parseInt(recepcionDatos.substring(j + 1, recepcionDatos.length()));
                        recepcionDatos = recepcionDatos.substring(0, j);
                    }
                }

                System.out.println("" + id_cliente);

                cliente = new ClienteTCP(id_cliente, socketConectado.getPort());

                do {
                    // Si la sala no esta llena añadir cliente
                    if (salas.get(i).getClientesSala() < max_clientes_sala) {
                        salas.get(i).anyadirCliente(cliente);
                        asignado = true;
                    } else if (salas.get(i).getClientesSala() >= max_clientes_sala) { //Si la sala esta llena, crear una nueva y añadir cliente
                        Sala s = new Sala(max_clientes_sala, serverSocket);
                        salas.add(s);
                        s.anyadirCliente(cliente);
                        iniciarReconocimientoVecinos(i);
                        i++;
                    }

                } while (asignado == false);

                for (int i = 0; i < salas.size(); i++) {
                    for (int j = 0; j < salas.get(i).getClientesSala(); j++) {
                        System.out.println("Cliente:\n Id: " + salas.get(i).getClientes().get(j).getId_cli() + "\n Puero: " + salas.get(i).getClientes().get(j).getPuerto() + "\n Direccion IP: " + salas.get(i).getClientes().get(j).getDirIP());
                    }
                }

                System.out.println("Recibo: " + recepcionDatos);

                // Contestar al Cliente
                mensajeEnviado.writeUTF(recepcionDatos);

                /*if (recepcionDatos.equals("FINAL"));
                 socketConectado.close();*/
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        }

    }

    public void iniciarReconocimientoVecinos(int sala) {
        for (int r = 0; r < salas.get(sala).getClientesSala(); r++) {
            salas.get(sala).getClientes().get(r).enviarAVecinos(sala,r);
        }
    }
}
