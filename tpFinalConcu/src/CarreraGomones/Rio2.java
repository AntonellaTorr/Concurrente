package CarreraGomones;


import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

//no van a poder hacer acquire de nuevos gomones si no terminaron 
public class Rio2 {
    private int cantIndividuales, cantDobles;
    private Semaphore gomonesIndividuales;
    private Semaphore gomonesDobles;
    private CyclicBarrier barrera;
    private Semaphore mutex;
    private int cantGomonesQueSeTiraron;
    private Semaphore ganador;
    private int cantGomonesQueSePuedenTirar;
    private Semaphore mandarSimple;
    private Semaphore mandarDoble;
    private Semaphore clientesBajarse;
    private Semaphore puedenTirarse;
    private int cantEnRio;
    private int cantIndActual, cantDoblesActual;
    private Runnable reseteador;

    public Rio2(int cantIndivuales, int cantDobles, int cantGomonesQueSePuedenTirar) {

        this.cantIndividuales = cantIndivuales;
        this.cantDobles = cantDobles;
        this.cantGomonesQueSePuedenTirar = cantGomonesQueSePuedenTirar;
        this.cantGomonesQueSeTiraron = 0;

        gomonesIndividuales = new Semaphore(this.cantIndividuales);
        gomonesDobles = new Semaphore(this.cantDobles * 2);
        mutex = new Semaphore(1);
        clientesBajarse = new Semaphore(0);

        ganador = new Semaphore(1);
        barrera = new CyclicBarrier(cantGomonesQueSePuedenTirar, reseteador);
        puedenTirarse = new Semaphore(cantGomonesQueSePuedenTirar);
        mandarSimple = new Semaphore(0);
        mandarDoble = new Semaphore(0);
        cantEnRio=0;

    }

    // metodos cliente
    public void liberarGomon() {
        try {
            Random r = new Random();
            if (r.nextInt(2) % 2 == 0) {

                gomonesIndividuales.acquire();
                System.out.println(Thread.currentThread().getName() + " se subio a uno"+
                " indivual" );
           
                mandarSimple.release();

            } else {

                gomonesDobles.acquire();
              
                System.out.println(Thread.currentThread().getName() + " se subio a uno doble");
                mandarDoble.release();
            }
        } catch (InterruptedException e) {

        }

    }

    public void bajarse() {
        try {
            clientesBajarse.acquire();

            System.out.println(Thread.currentThread().getName() + " se bajo");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // metodos de los gomones

    public void correrCarrera(int tipo) {
        try {
            if (tipo == 1) {
                mandarSimple.acquire();
              
               // System.out.println(Thread.currentThread().getName() + "se tomo un individual");

            } else {
                mandarDoble.acquire(2);
             
                //System.out.println(Thread.currentThread().getName() + "se tomo uno doble ");

            }
            puedenTirarse.acquire();
        
           

            barrera.await();
            
            mutex.acquire();

            if (tipo==1){
                cantIndActual++;
          

            }
            else{
                cantDoblesActual++;
           
            }
       
            
            cantEnRio++;
        
            mutex.release();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void finalizarCarrera() {
        try {
             if (ganador.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + " GANO !!!!!!!!!!!!!!!!!!!!1");

            }
            mutex.acquire();
            cantEnRio--;           
            //es decir soy el ultimo
            if (cantEnRio==0){
            
                //EERRORRRR
                gomonesIndividuales.release(cantIndActual);
                gomonesDobles.release(cantDoblesActual*2);
                clientesBajarse.release(cantIndActual + cantDoblesActual*2);
                cantIndActual=0;
                cantDoblesActual=0;
                ganador.release();
                puedenTirarse.release(cantGomonesQueSePuedenTirar);
               
              
            }

            mutex.release();
            
        
          

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public void resetearBarrera(){
          
        System.out.println("-------------------SE RESETEA LA BARRERA-------------------");
        barrera.reset();
            
    }

}
