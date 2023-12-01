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
public class ReseteadorBarreraDeSalida implements Runnable{

    private Rio r;

    public ReseteadorBarreraDeSalida(Rio r){
        this.r=r;
    }

    public void run(){
        System.out.println("EMPEZO LA CARRERA");
        
    }
}