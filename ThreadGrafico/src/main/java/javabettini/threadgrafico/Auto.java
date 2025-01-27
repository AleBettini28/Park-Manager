package javabettini.threadgrafico;

import java.util.logging.*;

public class Auto extends Thread{
    
    private Parcheggio parcheggio;
    private ParkCanvas canvas;
    private int n, coda;
    
    public Auto(Parcheggio parcheggio, ParkCanvas canvas, int n) {
        this.parcheggio = parcheggio;
        this.canvas = canvas;
        this.n = n;
    }
    
    public void run()
    {
        try {
            n = parcheggio.entra();
            coda = parcheggio.getCoda();
            n = canvas.entraMacchina(n, coda);
            Thread.sleep((long) (Math.random() * 40000));
            parcheggio.esce(n);
            canvas.esciMacchina(n);
        } catch (InterruptedException ex) {
            Logger.getLogger(Auto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}