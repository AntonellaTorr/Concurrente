/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hilos;

import actividades.ZonaDeEntrega;

/**
 *
 * @author male_
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
