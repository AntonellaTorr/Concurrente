/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.snorkeles;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author male_
 */
public class Cliente implements Runnable {
    private ZonaDeEntrega z;
    
    public Cliente( ZonaDeEntrega z){
        this.z=z;
    }
    
    public void run(){
        z.avisarEncargado();
        z.hacerFilaPatasRana();
        z.hacerFilaSalvavidas();
        z.hacerFilaSnorkel();
        System.out.println(Thread.currentThread().getName()+"Entre a la piletaaa");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
        }
        z.salirDeLaPileta();
    }
    
}
