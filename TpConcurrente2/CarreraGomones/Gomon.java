package universidad.concurrente.tpFinal.Gomones;

import java.util.LinkedList;



public class Gomon implements Runnable {
    private int tipo;
    private Rio r;

    public Gomon (int tipo, Rio r){
        this.tipo=tipo;
        this.r=r;    
    }
    
  
            
    public void run (){
        while(true){
        r.correrCarrera(tipo);
        this.simular();
        r.finalizarCarrera();
    }
    }
    
    public void simular (){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
        
    }
}