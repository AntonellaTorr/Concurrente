/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.snorkeles;

/**
 *
 * @author male_
 */
public class Encargado implements Runnable{
    private ZonaDeEntrega z;
    
    public Encargado(ZonaDeEntrega z){
        this.z=z;
    }
    
    public void run(){
        while(true){
            z.esperarCliente();
        }
    }
}
