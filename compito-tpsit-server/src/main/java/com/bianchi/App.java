package com.bianchi;

public class App 
{
    public static void main( String[] args )
    {
        ServerAccept serverAccept = new ServerAccept(6778);

        serverAccept.Start();
    }
}
