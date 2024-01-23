/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import actividades.ZonaDeEntrega;

/*
Disfruta de Snorkel ilimitado
existe la posibilidad de realizar snorkel en una laguna, para lo cual es necesario 
adquirir previamente el equipo de snorkel, salvavidas y patas de ranas, que deberán 
ser devueltos al momento de finalizar la actividad. En el ingreso a la actividad hay 
un stand donde dos asistentes entregan el equipo mencionado. La única limitación en 
cuanto a capacidad es dada por la cantidad de equipos completos (snorkel, salvavidas y patas de rana) existentes.
*/

public class EncargadoSnorkel implements Runnable{
    private ZonaDeEntrega z;
    
    public EncargadoSnorkel(ZonaDeEntrega z){
        this.z=z;
    }
    
    public void run(){
        while(true){
            z.esperarCliente();
        }
    }
}
