/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snorkeles;

/**
 *
 * @author male_
 */
public class main {
    public static void main(String[] args) {
        Cliente []c = new Cliente [12];
        Encargado []e = new Encargado[2];
        ZonaDeEntrega z = new ZonaDeEntrega(1,1,1);
        
        Thread []clientesitos = new Thread[c.length];
        Thread [] encargaditos = new Thread[e.length];
        
        
         for (int i = 0; i < encargaditos.length; i++) {
            encargaditos[i]= new Thread(new Encargado(z), "Encargado "+i);
            encargaditos[i].start();
        }
        for (int i = 0; i < clientesitos.length; i++) {
            clientesitos[i]= new Thread(new Cliente(z), "Cliente "+i);
            clientesitos[i].start();
        }
    }
    
}
