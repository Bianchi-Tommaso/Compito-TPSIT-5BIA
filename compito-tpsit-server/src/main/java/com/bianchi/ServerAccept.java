package com.bianchi;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerAccept 
{
    private int porta;              // Porta del Server
    ServerSocket server = null;     // Variabile per avviare il server in una specifica Porta
    Socket client;                  //Socket

    public ServerAccept(int porta) 
    {
        this.porta = porta;
    }

    public void Start() 
    {
        try 
        {
            server = new ServerSocket(porta); // Il Server si avvia aprendo la porta

            System.out.println("Server: Start");

            


            for (;;) // For per instanziare un Thread ogni volta che si connette un client
            {
                client = server.accept(); // Il Server attende

                ThreadClient threadClient = new ThreadClient(client);

                threadClient.start();
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }
    }
}