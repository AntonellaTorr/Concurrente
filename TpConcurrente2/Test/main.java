/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad.concurrente.tpFinal.Gomones;

import utiles.TecladoIn;

/**
 *
 * @author male_
 */


public class main {
 public static void main(String[] args) {
      
     System.out.println("ingrese la cantidad de gomones simples;");
     int gomonesSimples = TecladoIn.readLineInt();
     System.out.println("ingrese la cantidad de gomones dobles");
     int gomonesDobles = TecladoIn.readLineInt();
     
     System.out.println("ingrese la cantidad de gomones que se pueden tirar por carrera");
     int cantTirarse= TecladoIn.readLineInt();
     
     
     
       Rio rio= new Rio(gomonesSimples, gomonesDobles, cantTirarse);

        
     


        Thread []c= new Thread[20];
        Thread []g= new Thread[(gomonesSimples+gomonesDobles)];
   

       
       /* empCole.start();
  
       cocinero1.start();
       cocinero2.start();
*/
    
       
       
        for (int i=0; i<c.length;i++){
           c[i]= new Thread (new cliente(rio), "Cliente "+i );


        }
       
          
        
        
        for (int i=0; i<gomonesSimples;i++){
           g[i]= new Thread (new Gomon(1,rio) );

        }
       for (int i=gomonesSimples; i<gomonesDobles;i++){
           g[i]= new Thread (new Gomon(2,rio) );
       }

       for (int i=0; i<(gomonesDobles+gomonesSimples);i++){
           g[i].start();
       }
       
       for (int i=0; i<c.length;i++){
            c[i].start();

        }

  


        
 }   
}

