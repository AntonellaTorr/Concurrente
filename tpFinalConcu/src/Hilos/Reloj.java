package Hilos;

import actividades.Entrada;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
El complejo se encuentra abierto para el ingreso de 09:00 a 17:00hs. Considere que las
actividades cierran a las 18.00 hrs.
*/


public class Reloj implements Runnable {
    private Entrada entrada;

    public Reloj(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public void run(){
       while(true){
           try {
               Thread.sleep(2000);
               entrada.pasarHora();
           } catch (InterruptedException ex) {
               Logger.getLogger(Reloj.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   }
    
}
