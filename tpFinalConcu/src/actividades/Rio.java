package actividades;

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


import Hilos.ReseteadorBarreraDeSalida;
import Hilos.Reseteador;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

//no van a poder hacer acquire de nuevos gomones si no terminaron 
public class Rio {
    private int cantIndividuales, cantDobles;//La cantidad de gomones que hay para cada tipo
    private int cantGomonesQueSePuedenTirar;//Cantidad de gomones que se pueden tirar juntos
    private Semaphore gomonesIndividuales, gomonesDobles;//Representa los gomones que hay
    private Semaphore mutex;//Para exclusion mutua
    private Semaphore ganador;//Para saber quien gano
    private Semaphore mandarSimple;//Rendevous entre clientes y gomones
    private Semaphore mandarDoble;//Rendevous entre clientes y gomones
    private Semaphore clientesBajarse;//Rendevous para avisar a los clientes que pueden bajarse 
    private Semaphore esperarParaEmpezarLaCarrera; //para que no empiece una carrera antes de que finalice la otra

    private CyclicBarrier barrera, meta;//Representan las lineas de meta y de llegada 
    private int cantIndActual, cantDoblesActual;//La cantidad de cada tipo de gomon que se tiro en un juego 


    private Thread reseteadorMeta, reseteadorBarrera;

    public Rio(int cantIndivuales, int cantDobles, int cantGomonesQueSePuedenTirar) {

        this.cantIndividuales = cantIndivuales;
        this.cantDobles = cantDobles;
        this.cantGomonesQueSePuedenTirar = cantGomonesQueSePuedenTirar;

        gomonesIndividuales = new Semaphore(this.cantIndividuales);
        gomonesDobles = new Semaphore(this.cantDobles * 2);
        mutex = new Semaphore(1);
        clientesBajarse = new Semaphore(0);
        ganador = new Semaphore(1);
        mandarSimple = new Semaphore(0);
        mandarDoble = new Semaphore(0);
        esperarParaEmpezarLaCarrera= new Semaphore(cantGomonesQueSePuedenTirar);
    

        reseteadorMeta= new Thread(new Reseteador(this));
        reseteadorBarrera = new Thread(new ReseteadorBarreraDeSalida(this)) ;
       
       

        barrera = new CyclicBarrier(cantGomonesQueSePuedenTirar, reseteadorBarrera);
        meta = new CyclicBarrier(cantGomonesQueSePuedenTirar, reseteadorMeta);

       

    }
    
 
    
    
    // metodos cliente
    public void liberarGomon(int tipo) {
        try {
             if (tipo==1){

                gomonesIndividuales.acquire();
                System.out.println(Thread.currentThread().getName() + " agarro un gomon individual");
                mandarSimple.release();

            } else {
                gomonesDobles.acquire();
                System.out.println(Thread.currentThread().getName() + "agarro un gomon doble");
                mandarDoble.release();
            }
        } catch (InterruptedException e) {

        }

    }

    public void bajarse() {
        try {
            clientesBajarse.acquire();

            System.out.println(Thread.currentThread().getName() + " ya termino de correr, asi que se fue");
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
                System.out.println("--------HAY UN GOMON INDIVIDUAL ESPERANDO EN LA BARRERA------");

            } else {
                mandarDoble.acquire(2);
                System.out.println("--------HAY UN GOMON DOBLE LLENO ESPERANDO EN LA BARRERA------");

                // System.out.println(Thread.currentThread().getName() + "se tomo uno doble ");

            }

            esperarParaEmpezarLaCarrera.acquire();
            barrera.await();

            mutex.acquire();

            if (tipo == 1) {
                cantIndActual++;

            } else {
                cantDoblesActual++;

            }

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
            meta.await();

        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    
    public void resetearJuego() {
        try {
            mutex.acquire();
            gomonesIndividuales.release(cantIndActual);
            gomonesDobles.release(cantDoblesActual * 2);
            clientesBajarse.release((cantIndActual + (cantDoblesActual * 2)));
            cantIndActual = 0;
            cantDoblesActual = 0;
            ganador.release();
            esperarParaEmpezarLaCarrera.release(cantGomonesQueSePuedenTirar);
            mutex.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
               

    }

}