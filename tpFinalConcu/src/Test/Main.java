/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import Transporte.Gomon;
import actividades.Rio;
import utiles.TecladoIn;

/**
 *
 * @author male_
 */


public class Main {
 public static void main(String[] args) {
      
     System.out.println("ingrese la cantidad de gomones simples;");
     int gomonesSimples = TecladoIn.readLineInt();
     System.out.println("ingrese la cantidad de gomones dobles");
     int gomonesDobles = TecladoIn.readLineInt();
     
     System.out.println("ingrese la cantidad de gomones que se pueden tirar por carrera");
     int cantTirarse= TecladoIn.readLineInt();
     
     
     
       Rio rio= new Rio(gomonesSimples, gomonesDobles, cantTirarse);

      
        
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

