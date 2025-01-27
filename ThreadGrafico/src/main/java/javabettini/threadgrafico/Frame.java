package javabettini.threadgrafico;

import java.awt.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Frame extends JFrame{
    
    private Parcheggio parcheggio;
    
    public Frame() {
        
        Container c = this.getContentPane();
        c.setLayout(null);
        
        int capacità = 5;
        int postiliberi=5;
        Semaphore S1 = new Semaphore(postiliberi);
        
        ParkCanvas canvas = new ParkCanvas(this);
        canvas.setBounds(0, 0, 1800, 1000);
        canvas.setBackground(Color.darkGray);
        c.add(canvas);
        
        parcheggio = new Parcheggio(capacità, postiliberi, S1, canvas);
        
        //END
        pack();
        setSize(1500, 950);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        //while infinito per la generazione dei Thread (Macchine)
        while(true)
        {
            new Auto(parcheggio, canvas, 0).start();
            try {
                Thread.sleep((long) ((Math.random() * 6000) + 4000));    //tempo tra l'arrivo di una macchina e l'altra
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Parcheggio getParcheggio(){
        return parcheggio;
    }
}