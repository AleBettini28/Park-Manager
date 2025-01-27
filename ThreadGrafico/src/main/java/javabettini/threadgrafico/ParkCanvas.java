package javabettini.threadgrafico;

import java.awt.*;
import static java.lang.Thread.sleep;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ParkCanvas extends Canvas {

    private Frame frame;
    
    public ParkCanvas(Frame frame) {
        this.frame = frame;
    }

    private Graphics gFin;
    private boolean semaforo = true;
    private boolean waited[] = new boolean[5];
    private final int park1X = 390, park2X = 550, park3X = 710, park4X = 870, park5X = 1030;
    private final int park1 = 450, park2 = 610, park3 = 770, park4 = 930, park5 = 1090;
    private int parkedCars = 0;
    private int flag1 = 0, flag2 = 0, flag3 = 0, flag4 = 0, flag5 = 0;
    private int codaVal = 0;
    
    //METODO CHE DISEGNA PER PRIMO SULLA CANVAS QUANDO CREATA; DISEGNA IL PARCHEGGIO, LA STRADA E IL SEMAFORO
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int i = 0; i < 5; i++){
            waited[i] = false;
        }
        
        g.setColor(Color.white);

        // Disegna il bordo del parcheggio
        g.drawRect(400, 250, 800, 600); //PARCHEGGIO

        g.drawRect(0, 650, 395, 150);   //STRADA INGRESSO
        g.drawRect(1205, 650, 1000, 150);    //STRADA USCITA

        g.clearRect(0, 650, 1, 149);
        g.clearRect(394, 650, 150, 150);    //BUCO INGRESSO
        g.clearRect(1100, 650, 110, 150);   //BUCO USCITA
        
        //DISEGNA SFONDO SEMAFORO E IMPOSTA IL SEMAFORO VERDE
        g.setColor(Color.black);
        g.drawRect(300, 550, 51, 51);
        g.fillRect(301, 551, 50, 50);

        semaforoVerde();
        g.setColor(Color.white);
        
        //POSTI AUTO
        for (int i = 0; i < 5; i++) {
            int x = i * 160;
            g.drawRect(x + 400, 250, 160, 200);     //160 larghezza parcheggio 200 lunghezza parcheggio
        }
    }
    
    //SETTA IL SEMAFORO A VERDE
    public void semaforoVerde() {
        Graphics g = this.getGraphics();
        g.setColor(Color.GREEN);
        semaforo = true;
        g.fillOval(300, 550, 51, 51);
        g.setColor(Color.white);
    }

    //SETTA IL SEMAFORO A ROSSO
    public void semaforoRosso() {
        Graphics g = this.getGraphics();
        g.setColor(Color.RED);
        semaforo = false;
        g.fillOval(300, 550, 51, 51);
        g.setColor(Color.white);
    }

    /*FUNZIONE CHE DISEGNA L'INGRESSO DELLA MACCHINA NEL POSTO AUTO
    IN CUI DEVE ENTRARE.
    LA FUNZIONE CONTROLLA SE IL POSTO E' LIBERO, IN CASO SIA OCCUPATO CHIAMA SE STESSA
    PASSANDO COME PARAMETRO IL NUMERO DI PARCHEGGIO SEGUENTE, FINO A TROVARE IL PARCHEGGIO LIBERO ED ENTRARCI.
    LA FUNZIONE E' COMPOSTA DA UNO SWITCH DEL POSTO AUTO IN CUI ENTRARE, COSI CHE LA MACCHINA
    SEGUA DELLE POSIZIONE PREIMPOSTATE (VARIABILI PARKn e PARKnX (n = 1, 2, 3, 4, 5)) PER PARCHEGGIARE CORRETTAMENTE.
    */
    public int entraMacchina(int posto, int coda) {
        Graphics g = this.getGraphics();
        g.setColor(Color.yellow);
        
        if (semaforo) {
            switch (posto) {
                case 1:
                    if(flag1 == 1){
                        int temp = entraMacchina(2, 0);
                        return temp;
                    }
                    
                    parkedCars++;
                    flag1 = 1;
                    if(!waited[0]){
                        waited[0] = false;
                        System.out.println("Entra senza essersi fermata");
                        for (int i = 0; i < park1X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 140, 51);
                            } catch (InterruptedException e) {}
                        }
                    }
                    else if(coda > 0){
                        semaforoMacchina(posto);
                    }
                    else{
                        System.out.println("Entra dopo semaforo rosso");
                        for (int i = (380 - ((codaVal + 1)  * 140)); i < park1X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 140, 51);
                            } catch (InterruptedException e) {}
                        }
                    }

                    g.clearRect(park1X, 700, 170, 60);
                    for (int y = 620; y > 260; y--) {
                        g.drawRect(park1, y, 50, 130);
                        g.fillRect(park1, y, 50, 130);
                        
                        try {
                            sleep(2);
                            g.clearRect(park1, y, 51, 140);

                        } catch (InterruptedException e) {

                        }
                    }
                    g.drawRect(park1, 260, 50, 130);
                    g.fillRect(park1, 260, 50, 130);

                    break;

                case 2:
                    if(flag2 == 1){
                        int temp = entraMacchina(3, 0);
                        return temp;
                    }
                    
                    parkedCars++;
                    flag2 = 1;
                    if(!waited[1]){
                        waited[1] = false;
                        for (int i = 0; i < park2X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);

                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }
                    else if(coda > 0){
                        semaforoMacchina(posto);
                    }
                    else{
                        for (int i = (380 - ((codaVal + 1)  * 140)); i < park2X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }

                    g.clearRect(park2X, 700, 170, 60);
                    for (int y = 620; y > 260; y--) {
                        g.drawRect(park2, y, 50, 130);
                        g.fillRect(park2, y, 50, 130);

                        try {
                            sleep(2);
                            g.clearRect(park2, y, 51, 131);

                        } catch (InterruptedException e) {

                        }
                    }
                    g.drawRect(park2, 260, 50, 130);
                    g.fillRect(park2, 260, 50, 130);
                    
                    break;

                case 3:
                    if(flag3 == 1){
                        int temp = entraMacchina(4, 0);
                        return temp;
                    }
                    
                    parkedCars++;
                    flag3 = 1;
                    if(!waited[2]){
                        waited[2] = false;
                        for (int i = 0; i < park3X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);

                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }
                    else if(coda > 0){
                        semaforoMacchina(posto);
                    }
                    else{
                        for (int i = (380 - ((codaVal + 1)  * 140)); i < park3X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }

                    g.clearRect(park3X, 700, 170, 60);
                    for (int y = 620; y > 260; y--) {
                        g.drawRect(park3, y, 50, 130);
                        g.fillRect(park3, y, 50, 130);

                        try {
                            sleep(2);
                            g.clearRect(park3, y, 51, 131);

                        } catch (InterruptedException e) {

                        }
                    }
                    g.drawRect(park3, 260, 50, 130);
                    g.fillRect(park3, 260, 50, 130);

                    break;

                case 4:
                    if(flag4 == 1){
                        int temp = entraMacchina(5, 0);
                        return temp;
                    }
                    
                    parkedCars++;
                    flag4 = 1;
                    if(!waited[3]){
                        waited[3] = false;
                        for (int i = 0; i < park4X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);

                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }
                    else if(coda > 0){
                        semaforoMacchina(posto);
                    }
                    else{
                        for (int i = (380 - ((codaVal + 1)  * 140)); i < park4X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }

                    g.clearRect(park4X, 700, 170, 60);
                    for (int y = 620; y > 260; y--) {
                        g.drawRect(park4, y, 50, 130);
                        g.fillRect(park4, y, 50, 130);

                        try {
                            sleep(2);
                            g.clearRect(park4, y, 51, 131);

                        } catch (InterruptedException e) {

                        }
                    }
                    g.drawRect(park4, 260, 50, 130);
                    g.fillRect(park4, 260, 50, 130);

                    break;

                case 5:
                    if(flag5 == 1){
                        int temp = entraMacchina(1, 0);
                        return temp;
                    }
                    
                    parkedCars++;
                    flag5 = 1;
                    if(!waited[4]){
                        waited[4] = false;
                        for (int i = 0; i < park5X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);

                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }
                    else if(coda > 0){
                        semaforoMacchina(posto);
                    }
                    else{
                        for (int i = (380 - ((codaVal + 1)  * 140)); i < park5X; i++) {
                            g.drawRect(i, 700, 130, 50);
                            g.fillRect(i, 700, 130, 50);
                            try {
                                sleep(2);
                                g.clearRect(i, 700, 71, 51);
                            } catch (InterruptedException e) {}
                        }
                    }

                    g.clearRect(park5X, 700, 170, 60);
                    for (int y = 620; y > 260; y--) {
                        g.drawRect(park5, y, 50, 130);
                        g.fillRect(park5, y, 50, 130);

                        try {
                            sleep(2);
                            g.clearRect(park5, y, 51, 131);

                        } catch (InterruptedException e) {

                        }
                    }
                    g.drawRect(park5, 260, 50, 130);
                    g.fillRect(park5, 260, 50, 130);

                    break;
            }
            
            if(parkedCars == 5)
                semaforoRosso();
            
            g.setColor(Color.white);
            for (int i = 0; i < 5; i++) {
                int x = i * 160;
                g.drawRect(x + 400, 250, 160, 200);     //160 larghezza parcheggio 200 lunghezza parcheggio
            }
            
            return posto;
            
        }
        return 0;
    }
    
    /* LA FUNZIONE VIENE CHIAMATA QUANDO IL SEMAFORO E' ROSSO
    E DISEGNA LA MACCHINA MUOVENDOLA SOLO FINO AL PUNTO DI STOP.
    */
    public int semaforoMacchina(int n){
        Graphics g = this.getGraphics();
        g.setColor(Color.yellow);
        codaVal++;
        
        for(int i = 0; i < (380 - (codaVal * 140)); i++){
                g.drawRect(i, 700, 130, 50);
                g.fillRect(i, 700, 130, 50);
                
                try{
                    sleep(2);
                    g.clearRect(i, 700, 131, 51);
                }catch(InterruptedException e){}
            }
            
        while(!semaforo){
            g.drawRect((380 - (codaVal * 140)), 700, 130, 50);
            g.fillRect((380 - (codaVal * 140)), 700, 130, 50);
            waited[n] = true;
        }
        
        codaVal--;
        
        return codaVal;
    }

    /* LA FUNZIONE VIENE CHIAMATA QUANDO LA MACCHINA FINISCE IL SUO TEMPO
    IN SOSTA ED ESCE DAL PARCHEGGIO. PRENDE IN INGRESSO UN PARAMETRO
    CHE E' IL NUMERO DEL PARCHEGGIO DA DOVE STA USCENDO. IL VALORE DI QUESTO PARAMETRO
    VIENE CONTROLLATO CON UNO SWITCH COSì DA POTER RIDISEGNARE LA MACCHINA NELLE COORDINATE CORRETTE.
    */
    public void esciMacchina(int num) {
        Graphics g = this.getGraphics();
        g.setColor(Color.yellow);
        parkedCars--;
        
        switch (num) {
            case 1:
                flag1 = 0;
                for (int y = 260; y < 620; y++) {
                    g.drawRect(park1, y, 50, 130);
                    g.fillRect(park1, y, 50, 130);

                    try {
                        sleep(2);
                        g.clearRect(park1, y, 51, 131);
                    } catch (InterruptedException e) {}
                }

                for (int i = park1X; i < 1300; i++) {
                    g.drawRect(i, 700, 130, 50);
                    g.fillRect(i, 700, 130, 50);

                    try {
                        sleep(2);
                        g.clearRect(i, 700, 71, 51);
                    } catch (InterruptedException e) {}
                }
                break;

            case 2:
                flag2 = 0;
                for (int y = 260; y < 620; y++) {
                    g.drawRect(park2, y, 50, 130);
                    g.fillRect(park2, y, 50, 130);

                    try {
                        sleep(2);
                        g.clearRect(park2, y, 51, 131);
                    } catch (InterruptedException e) {}
                }

                for (int i = park2X; i < 1300; i++) {
                    g.drawRect(i, 700, 130, 50);
                    g.fillRect(i, 700, 130, 50);

                    try {
                        sleep(2);
                        g.clearRect(i, 700, 71, 51);
                    } catch (InterruptedException e) {}
                }

                break;

            case 3:
                flag3 = 0;
                for (int y = 260; y < 620; y++) {
                    g.drawRect(park3, y, 50, 130);
                    g.fillRect(park3, y, 50, 130);

                    try {
                        sleep(2);
                        g.clearRect(park3, y, 51, 131);
                    } catch (InterruptedException e) {}
                }

                for (int i = park3X; i < 1300; i++) {
                    g.drawRect(i, 700, 130, 50);
                    g.fillRect(i, 700, 130, 50);

                    try {
                        sleep(2);
                        g.clearRect(i, 700, 71, 51);
                    } catch (InterruptedException e) {}
                }
                break;

            case 4:
                flag4 = 0;
                for (int y = 260; y < 620; y++) {
                    g.drawRect(park4, y, 50, 130);
                    g.fillRect(park4, y, 50, 130);

                    try {
                        sleep(2);
                        g.clearRect(park4, y, 51, 131);
                    } catch (InterruptedException e) {}
                }

                for (int i = park4X; i < 1300; i++) {
                    g.drawRect(i, 700, 130, 50);
                    g.fillRect(i, 700, 130, 50);

                    try {
                        sleep(2);
                        g.clearRect(i, 700, 71, 51);
                    } catch (InterruptedException e) {}
                }

                break;

            case 5:
                flag5 = 0;
                for (int y = 260; y < 620; y++) {
                    g.drawRect(park5, y, 50, 130);
                    g.fillRect(park5, y, 50, 130);

                    try {
                        sleep(2);
                        g.clearRect(park5, y, 51, 131);
                    } catch (InterruptedException e) {}
                }

                for (int i = park5X; i < 1300; i++) {
                    g.drawRect(i, 700, 130, 50);
                    g.fillRect(i, 700, 130, 50);

                    try {
                        sleep(2);
                        g.clearRect(i, 700, 71, 51);
                    } catch (InterruptedException e) {}
                }

                break;
        }
        
        g.clearRect(1300, 700, 140, 60);
        g.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            int x = i * 160;
            g.drawRect(x + 400, 250, 160, 200);     //160 larghezza parcheggio 200 lunghezza parcheggio
        }
    }
    
    // RITORNA VERO SE IL SEMAFORO E' ROSSO, FALSO SE E' VERDE
    public boolean isRed(){
        if(!semaforo)
            return true;
        else
            return false;
    }
}