package com.bianchi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadClient extends Thread
{
    int numeroEstratto = 0;
    boolean vittoria = true;    
    BufferedReader inDalClient;     //Stream input Client
    DataOutputStream outVersoClient;    //Stream OutputClient
    Socket client;                      //Socket
    Logica logica = new Logica();       //Logica del server

    public ThreadClient(Socket client) throws IOException
    {
        this.client = client;   
        
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());

        outVersoClient.writeBytes("S: Connessione Effettuata" + '\n');
    }

    public void run()
    {
        for(;vittoria;)
        {
            try 
            {
            
                outVersoClient.writeBytes("S: Dammi Il Numero Estratto" + '\n');

                numeroEstratto = Integer.valueOf(inDalClient.readLine());   //Attendo il Numero

                if(logica.ControlloNumero(numeroEstratto))      //Controllo Se Il Numero è Già Statp Inserito
                {
                    outVersoClient.writeBytes("S: Numero Gia Inserito" + '\n');
                    outVersoClient.writeBytes("S: Riprova" + '\n');
                }
                else
                {
                    logica.AggiungiNumeroHash(numeroEstratto);      //Aggiungo il numero al HasMap e al Vector 
                    logica.AggiungiNumeroVector(numeroEstratto);
                    outVersoClient.writeBytes("S: Numeri Estratti " + logica.StampaNumeri() + '\n');    //Stampo i numeri estratti
                    
                    if(logica.Vittoria())       //Controll se il Client ha vinto
                    {
                        vittoria = false;
                        outVersoClient.writeBytes("S: COMPLIMENTI HAI VINTO!! END" + '\n');     //Stampo che il Client ha vinto
                    } 
                    else 
                    {
                        outVersoClient.writeBytes("S: Riprova" + '\n');
                    }
                }
           
            }    
            catch (IOException e) 
            {
            
                e.printStackTrace();
            }
        }

        try 
        {   //Chiudo la connessione
            
            outVersoClient.close();
            inDalClient.close();
            client.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        
    }
}