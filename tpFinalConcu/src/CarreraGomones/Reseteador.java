/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;

/**
 *
 * @author male_
 */


public class Reseteador implements Runnable{

    private Rio r;

    public Reseteador(Rio r){
        this.r=r;
    }

    public void run(){
        System.out.println("FINALIZO LA CARRERA");
        
        r.resetearJuego();
    }
}