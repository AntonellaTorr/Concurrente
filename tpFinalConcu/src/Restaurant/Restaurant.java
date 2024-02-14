package Restaurant;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    private int capacidad, nroRes;
    /*private ReentrantLock mutexAlmuerzo= new ReentrantLock();
    private ReentrantLock mutexMerienda= new ReentrantLock();
    private ReentrantLock mutexIngreso= new ReentrantLock();

    private Condition hayAlmuerzos= mutexAlmuerzo.newCondition();
    private Condition hayMeriendas= mutexMerienda.newCondition();
    private Condition hayLugar= mutexIngreso.newCondition()Â¨
    */

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
        System.out.println(Thread.currentThread().getName()+ " consumio almuerzo en " +nroRes);
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
          System.out.println(Thread.currentThread().getName()+ " consumio merienda en "+nroRes);
    }
    
    public synchronized void salirRestaurante(){
        cantPersonasAdentro--;
        this.notifyAll();
         System.out.println(Thread.currentThread().getName()+ " se fue de "+nroRes);
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
        System.out.println("----------------------cocinero----------------");
          System.out.println(Thread.currentThread().getName()+ " cocino meriendas");
          System.out.println("----------------------------------------------------");
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
         System.out.println(Thread.currentThread().getName()+ " cocino almuerzos");
        this.notifyAll();
    }
    
    
    }

