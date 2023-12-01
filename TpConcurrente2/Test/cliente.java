/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;

import java.util.Random;


public class cliente implements Runnable {


    private Rio rio;
   


    public cliente ( Rio rio){
        
        this.rio=rio;
    }


    public void run (){
        System.out.println(Thread.currentThread().getName()+ " se desperto");
        rio.liberarGomon(1);
        rio.bajarse();
    
    
    }
  

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    public void realizarNadoSnorkel() {



    }

  


}
  



