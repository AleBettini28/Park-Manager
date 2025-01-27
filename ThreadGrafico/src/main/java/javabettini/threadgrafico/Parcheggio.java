package javabettini.threadgrafico;

import java.util.concurrent.*;
import java.util.logging.*;

public class Parcheggio {
    
    private int capacità, postiliberi;
    private final Semaphore S1;
    private ParkCanvas pc;
    private int parkCount = 0;
    private int cont = 0;
    private int array[] = {0, 0, 0, 0, 0};
    private int coda = 0;
    
    public Parcheggio(int capacità, int postiliberi, Semaphore S1, ParkCanvas pc) {
        this.capacità = capacità;
        this.postiliberi = postiliberi;
        this.S1 = S1;
        this.pc = pc;
    }
    
    public int entra()
    {
        if(cont%5 == 0)
            parkCount = 0;
        
        //controllo semaforo rosso, chiamata a funzione semaforoMacchina che ferma la macchina nella posizione corretta
        if(pc.isRed()){
            System.out.println("Seamforo rosso, attesa in coda");
            coda = pc.semaforoMacchina(parkCount);
        }
        
        try {
            S1.acquire();
            postiliberi--;
            parkCount++;
            cont++;
            
            if(postiliberi != 0){
                pc.semaforoVerde();
            }
            
            System.out.println("Auto entrata posti liberi: "+postiliberi+"/"+capacità);
        } catch (InterruptedException ex) {
            System.out.println("error");
        }
        
            
        return parkCount;
    }
    
    public int getCoda(){
        return coda;
    }
    
    public void esce(int n)
    {
        S1.release();
        postiliberi++;
        pc.semaforoVerde();
        System.out.println("Auto uscita posti liberi: "+postiliberi+"/"+capacità);
    }
}