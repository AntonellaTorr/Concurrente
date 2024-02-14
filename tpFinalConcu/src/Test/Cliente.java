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
     Random r = new Random();
            if (r.nextInt(2) % 2 == 0)
                 rio.liberarGomon(1);
            else
                rio.liberarGomon(2);
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
  



