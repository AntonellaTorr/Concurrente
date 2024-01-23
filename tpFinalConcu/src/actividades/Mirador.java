package actividades;

import java.util.concurrent.Semaphore;

public class Mirador {
    private Semaphore mutexToboganIzq, mutexToboganDer; //porque solo 1 cliente se tira por vez
    private Semaphore avisoEncargado, cantMaxClientesFila, puedoTirarme;
    private Semaphore clienteAtendido; 
    private Semaphore mutexChar; 
    private char toboganLibre;
    
    public Mirador(int cantEscalones){
        this.mutexChar= new Semaphore(1);
        this.clienteAtendido= new Semaphore(1,true);
        this.mutexToboganDer= new Semaphore(1);
        this.mutexToboganIzq= new Semaphore(1);
        this.avisoEncargado= new Semaphore(0);
        cantMaxClientesFila= new Semaphore(cantEscalones);
        this.puedoTirarme= new Semaphore(0);
    }
    
    public void entrarALaFila(){

        try{
            System.out.println(Thread.currentThread().getName()+": Estoy esperando en la fila");
        cantMaxClientesFila.acquire();
        
    }catch(Exception e){}
    }
    
    public void primeroDeLaFila(){
        try {
            clienteAtendido.acquire();
        } catch (Exception e) {
        }
    }
    
    public void avisoEncargado(){
        avisoEncargado.release();
    }
    
    public char tirarsePorTobogan(){
        char t='p';
        try {
            //puede hacer acquire del sem una vez que el empleado haya determinado por que tobogan se va a tirar
           puedoTirarme.acquire();

           mutexChar.acquire();
           t=toboganLibre;
           mutexChar.release();

           //se puede atender a otro cliente y por ende hay otro espacio en la cola
           clienteAtendido.release();
           cantMaxClientesFila.release();
           
           if(t=='I'){
               mutexToboganIzq.acquire();
           }else{
               mutexToboganDer.acquire();
           }
        } catch (Exception e) {
        }
        return t;
    }
    
    
    public void liberarTobogan(char t){
        if(t=='I')
            mutexToboganIzq.release();
        else
            mutexToboganDer.release();
    }
    
    public void buscarToboganyLiberarPuedeTirarse(){
        try {
            avisoEncargado.acquire();
            if(mutexToboganIzq.tryAcquire()){
                System.out.println("Esta libre el tobogan de la izq");
                mutexChar.acquire();
                toboganLibre='I';
                mutexChar.release();
                mutexToboganIzq.release();
            }
            else{
                if(mutexToboganDer.tryAcquire()){
                    System.out.println("Esta libre el tobogan de la der");
                    mutexChar.acquire();
                    toboganLibre='D';
                    mutexChar.release();
                    mutexToboganDer.release();
                }
                else{
                    //estan los dos toboganes ocupados, el cliente se queda esperando en alguno random
                    int tob= (int)(Math.random()*2)+1;
                    System.out.println("Estan los dos toboganes ocupados");
                    if(tob==1){
                        mutexChar.acquire();
                        System.out.println("Le dije al cliente que espere por el tobogan de la izq");
                        toboganLibre='I';
                        mutexChar.release();
                    }
                    else{
                        mutexChar.acquire();
                        System.out.println("Le dije al cliente que espere por el tobogan de la der");
                        toboganLibre='D';
                        mutexChar.release();
                }
                }
                
                    
            }
            puedoTirarme.release(); //como siempre se va a quedar en alguno trabado, libreo este permiso
        } catch (Exception e) {
        }
    }
    

}
