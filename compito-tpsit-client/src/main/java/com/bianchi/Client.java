package com.bianchi;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client 
{
    private String nomeServer;
    private int portaServer;
    private String numeroInserito;
    boolean vittoria = true;
    String rispostaServer;
    Socket mioSocket;
    BufferedReader tastiera;
    DataOutput outVersoServer;
    BufferedReader inDalServer;
    
    public Client(String nomeServer, int portaServer) 
    {
        this.nomeServer = nomeServer;
        this.portaServer = portaServer;
    }

    public Socket Connetti() throws IOException
    {
        mioSocket = new Socket(nomeServer, portaServer);        //Connetto Al Server

            tastiera = new BufferedReader(new InputStreamReader(System.in));    //Lettura input tastiera
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream()); 
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));    

            System.out.println(inDalServer.readLine());

            return mioSocket;
    }

    public void Comunica() throws NumberFormatException, IOException
    {
        for(;vittoria;)
        {
            System.out.println(inDalServer.readLine());

            System.out.println("Inserire Un Numero");

            numeroInserito = tastiera.readLine();

            outVersoServer.writeBytes(numeroInserito + '\n');

            rispostaServer = inDalServer.readLine();
            System.out.println(rispostaServer);
            rispostaServer = inDalServer.readLine();
            
            if(rispostaServer.contains("END"))  //Se Il messaggio inviato dal Server contiene END, Significa che ho vinto e quindi mi Disconnetto
            {
                try 
                {
                    vittoria = false;   //Variabile per interrompere il Ciclo
                    inDalServer.close();
                    mioSocket.close();
                } 
                catch (IOException e) 
                {   
                    e.printStackTrace();
                }
            }

            rispostaServer = rispostaServer.replace("END", "");
            System.out.println(rispostaServer);     //Stampo la risposta del Server
            
        }
    }
}
