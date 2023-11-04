package CarreraGomones;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Rio {
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

    public Rio(int cantIndivuales, int cantDobles, int cantGomonesQueSePuedenTirar) {

        this.cantIndividuales = cantIndivuales;
        this.cantDobles = cantDobles;
        this.cantGomonesQueSePuedenTirar = cantGomonesQueSePuedenTirar;
        this.cantGomonesQueSeTiraron = 0;

        gomonesIndividuales = new Semaphore(this.cantIndividuales);
        gomonesDobles = new Semaphore(this.cantDobles*2);
        mutex = new Semaphore(1);
        clientesBajarse = new Semaphore(0);

        ganador = new Semaphore(1);
        barrera = new CyclicBarrier(cantGomonesQueSePuedenTirar);
        puedenTirarse = new Semaphore(cantGomonesQueSePuedenTirar);
        mandarSimple= new Semaphore(0);
        mandarDoble=new Semaphore(0);

    }

    // metodos cliente
    public void liberarGomon() {
        try {
            Random r = new Random();
            if (r.nextInt(2) % 2 == 0) {
             
                gomonesIndividuales.acquire();
              //  System.out.println(Thread.currentThread().getName() + " se subio a uno indivual" + " PERMISOS SIMPLE"+ gomonesIndividuales.availablePermits());
                mandarSimple.release();

            } else {

          
                gomonesDobles.acquire();
              //  System.out.println(Thread.currentThread().getName() + " se subio a uno doble" + " PERMISOS doble "+ gomonesDobles.availablePermits());
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
        System.out.println("GANADOR PERMISOS " +ganador.availablePermits());
        try {
            if (tipo == 1) {
                mandarSimple.acquire();
                System.out.println(Thread.currentThread().getName() + "se tomo un individual");

            } else {
                mandarDoble.acquire(2);
                System.out.println(Thread.currentThread().getName() + "se tomo uno doble ");

            }
            puedenTirarse.acquire();
            System.out.println(Thread.currentThread().getName() +"PUEDEN TIRARSE PERMISOS" + puedenTirarse.availablePermits());

            barrera.await();

            mutex.acquire();
            cantGomonesQueSeTiraron++;
            if (cantGomonesQueSeTiraron == cantGomonesQueSePuedenTirar) {
                System.out.println("-------------------SE RESETEA LA BARRERA-------------------");
                cantGomonesQueSeTiraron = 0;
                barrera.reset();
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
        if (ganador.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " GANO");

            gomonesIndividuales.release(cantIndividuales);
            gomonesDobles.release(cantDobles*2);
            puedenTirarse.release(cantGomonesQueSePuedenTirar);

            clientesBajarse.release(cantIndividuales + cantDobles * 2);
            ganador.release();

        }
    }

}
