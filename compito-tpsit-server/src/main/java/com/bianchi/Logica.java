package com.bianchi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

public class Logica 
{
    int conta;
    HashMap<Integer, Integer> contenitore = new HashMap<Integer, Integer>();    //un HashMap per usare come chiave i numeri, così posso controllare se sono già stati inseriti dei numeri 
    Vector<Integer> contenitoreNumeri = new Vector<Integer>();      //Contenitore per i numeri inseriti dal Client

    //Aggiungo il numero all'HasMap
    public void AggiungiNumeroHash(int x)
    {
        contenitore.put(x, x);
    }

    //Aggiungo il numero al Vector
    public void AggiungiNumeroVector(int x)
    {
        contenitoreNumeri.add(x);
    }

    //Controllo se un numero è gia stato isnerito
    public boolean ControlloNumero(int x)
    {
        return contenitore.containsKey(x);
    }

    //Stampa dei numeri estratti
    public String StampaNumeri()
    {
        String stampa = "";
        Collections.sort(contenitoreNumeri);

        for(int i = 0; i < contenitoreNumeri.size(); i++)
            stampa += contenitoreNumeri.get(i) + "-";

            return stampa;
    }

    //Metodo per controllare se il Client ha vinto
    public boolean Vittoria()
    {
        boolean vittoria = false;

        Collections.sort(contenitoreNumeri);

       for(int i = 0; i < contenitoreNumeri.size() - 1; i++)
        {
            if(contenitoreNumeri.get(i + 1) - contenitoreNumeri.get(i) == 1)    //Sottraggo gli elementi per verificare se sono consecutivi
            {
                conta++;    //Variabile per tenere in memoria se ci sono 5 numeri consecutivi
            }
            else
            {
                conta = 0;
            }

            if(conta == 4)
            {
                vittoria = true;
                break;
            }

        }
        conta = 0;

        return vittoria;
    }
}
