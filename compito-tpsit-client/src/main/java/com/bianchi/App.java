package com.bianchi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String ipAddress = "";      //Variabile di input per l'indirizzo IP per connettersi al Server
        String porta = "";          //Variabile di input per la porta del Server
        
        System.out.println("Inserire Indirizzo IP");
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ipAddress = br.readLine();  
        
        System.out.println("Inserire Porta Server");        
        porta = br.readLine();  

        Client client = new Client(ipAddress, Integer.valueOf(porta));
        
        client.Connetti();
        client.Comunica();
    }
}
