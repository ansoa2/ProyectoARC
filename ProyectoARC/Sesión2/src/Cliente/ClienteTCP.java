/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.net.InetAddress;
import java.net.Socket;
import static Cliente.Constantes.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author PauMiquel
 */
public class ClienteTCP {

    private Socket cs;
    private InetAddress dirIP;
    private TiempoCiclo tiempo;
    private int puerto;
    private DataInputStream in;
    private DataOutputStream out;
    private int id_cli;
    private static int next_id = 1;

    public ClienteTCP() throws UnknownHostException, IOException {
        this.dirIP = InetAddress.getByName(IP_SERVIDOR);
        this.tiempo = new TiempoCiclo();
        this.puerto = PUERTO_SERVIDOR;
        this.id_cli = next_id;
        
        next_id++;
        //this.envioDatos = "";
        //this.recepcionDatos = "";

        this.iniciarCiclo();
    }

    public InetAddress getDirIP() {
        return dirIP;
    }

    public void setDirIP(InetAddress dirIP) {
        this.dirIP = dirIP;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    public ClienteTCP(int id_cliente, int puerto) throws UnknownHostException, IOException {
        this.dirIP = InetAddress.getByName(IP_SERVIDOR);
        this.puerto = puerto;
        this.id_cli = id_cliente;
        
    }

    private void iniciarCiclo() {
        try {
            Thread.sleep(TIEMPO_REPETICION);
        } catch (InterruptedException e) {
            System.out.println("ERROR. " + e);
        }

        System.out.println(ANSI_RED + "SOY EL CLIENTE: " + id_cli);
        
        try {
            // Establecer conexion y canales de E/S con el servidor
            cs = new Socket(IP_SERVIDOR, 2017);
            in = new DataInputStream(cs.getInputStream());
            out = new DataOutputStream(cs.getOutputStream());
        } catch (IOException e) {
            System.out.println("No puede establer canales de E/S para la conexión");
            System.err.println(e);
        }

        try {
            // Generar una localizacion y convertir a String
            Localizacion localizacion = new Localizacion();
            String envioDatos = localizacion.toString() + "$" + id_cli;

            // Enviar localizacion al servidor
            out.writeUTF(envioDatos);
            // Recibir respuesta del servidor
            String recepcionDatos = in.readUTF();
            // Mostrar respuesta del servidor
            System.out.println("SERVIDOR DICE: " + recepcionDatos);

            cs.close();
        } catch (IOException e) {
            System.out.println("ERROR. No se ha podido realizar el intercambio de datos con el servidor.");
            System.err.println(e);
        }
    }
    
    public int getId_cli() {
        return id_cli;
    }

    public void setId_cli(int id_cli) {
        this.id_cli = id_cli;
    }

    public void enviarAVecinos(int sala, int cliente) {
         try {
            // Generar una localizacion y convertir a String
            Localizacion localizacion = new Localizacion();
            String envioDatos = localizacion.toString() + "$" + id_cli;

            // Enviar localizacion al servidor
            out.writeUTF(envioDatos);
            // Recibir respuesta del servidor
            String recepcionDatos = in.readUTF();
            // Mostrar respuesta del servidor
            System.out.println("SERVIDOR DICE: " + recepcionDatos);

            cs.close();
        } catch (IOException e) {
            System.out.println("ERROR. No se ha podido realizar el intercambio de datos con el servidor.");
            System.err.println(e);
        }
    }

}
