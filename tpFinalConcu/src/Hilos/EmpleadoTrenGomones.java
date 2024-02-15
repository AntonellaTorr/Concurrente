/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import Transporte.Tren;

/* Carrera de gomones por el río
esta actividad permite que los visitantes bajen por el rio, que se encuentra rodeado de manglares, 
compitiendo entre ellos. Para ello es necesario llegar hasta el inicio de la actividad 
a través de bicicletas que se prestan es un stand de bicicletas, o a través de un tren interno 
que tiene una capacidad de 15 personas como máximo. Al llegar al inicio del recorrido cada
persona dispondrá de un bolso con llave, en donde podrá guardar todas las pertenencias que no quiera
mojar. Los bolsos están identificados con un número al igual que la llave, los bolsos
serán llevados en una camioneta, hasta el final del recorrido en donde podrán ser
retirados por el visitante. Para bajar se utilizan gomones, individuales o con
capacidad para 2 personas. La cantidad de gomones de cada tipo es limitada. Para
habilitar una largada es necesario que haya h gomones listos para salir, no importa
el tipo.
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
            this.simulaViaje(); //el tiempo que le toma ir
            t.avisarLlegadaDestino();
            this.simulaViaje(); //el tiempo que le toma volver

            }
        }
        
        private void simulaViaje(){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
        
    }


