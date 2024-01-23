/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Transporte.Tren;

/**
 *
 * @author male_
 */
public class EmpleadoTrenGomones implements Runnable{
     Tren t;
        
        public EmpleadoTrenGomones(Tren t){
            this.t=t;
        }
        
        public void run(){
            while(true){
            t.posicionarseEnPuerta();
            t.iniciarViaje();
            this.simulaViaje();
            t.avisarLlegadaDestino();
            this.simulaViaje();

            }
        }
        
        private void simulaViaje(){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
        
    }

}
