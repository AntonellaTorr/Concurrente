package actividades;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
Restaurante:
En el pago del acceso al Parque se encuentra incluido el almuerzo y la
merienda. Existen tres restaurantes, pero solamente se puede consumir un almuerzo
y una merienda en cualquiera de ellos. Puede tomar el almuerzo en un restaurante y
la merienda en otro. Los restaurantes tienen capacidad limitada. Las personas son
atendidas en orden de llegada. Los restaurantes tienen habilitada una cola de espera.
*/

public class Restaurant {
    private int capacidad, nroRes;
    private int cantAlmuerzosDisponibles, cantMeriendasDisponibles, cantPersonasAdentro;


    public Restaurant (int capacidad, int nroRes){
        this.nroRes=nroRes;
        this.capacidad=capacidad;
        cantPersonasAdentro=0;
        cantAlmuerzosDisponibles=0;
        cantMeriendasDisponibles=0;

    }
 
     /*----------------------------------------------------METODOS CLIENTE----------------------------------------------------*/
    
    public synchronized void ingresar(){
        while(cantPersonasAdentro>capacidad){
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
            
        }
        
        //si no, entra jiji
        cantPersonasAdentro++;
    }
    
    public synchronized void consumirAlmuerzo(){
        while(cantAlmuerzosDisponibles==0){
            try {
                this.notifyAll();
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        cantAlmuerzosDisponibles--;
    }
    
    public synchronized void consumirMerienda(){
        while(cantMeriendasDisponibles==0){
            try {
                  this.notifyAll();
                this.wait();
            } catch (Exception e) {
                // TODO: handle exception
            }
              
        }
        cantMeriendasDisponibles--;
    }
    
    public synchronized void salirRestaurante(){
        cantPersonasAdentro--;
        this.notifyAll();
    }
    
    
    /*----------------------------------------------------METODOS COCINERO----------------------------------------------------*/
    
    public synchronized void cocinarMeriendas(){
        while(cantMeriendasDisponibles>0){
            try {
                this.wait();
            } catch (InterruptedException ex) {
              
            }
        }
        cantMeriendasDisponibles=5;
        this.notifyAll();
    }
    
    public synchronized void cocinarAlmuerzos(){
        while(cantAlmuerzosDisponibles>0){
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        cantAlmuerzosDisponibles=5;
        this.notifyAll();
    }
    
    
    }

