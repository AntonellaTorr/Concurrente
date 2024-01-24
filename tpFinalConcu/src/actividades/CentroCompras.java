package actividades;

import java.util.concurrent.Semaphore;


/*
En el shop se pueden adquirir suvenires de distinta clase, los cuales se pueden abonar en
una de las dos cajas disponibles.
*/


public class CentroCompras {
    private Semaphore cajasDisponibles= new Semaphore(2); //lo aclara el enunciado

    public void irAPagar(){

        try {
            cajasDisponibles.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } 

    public void finalizarPago(){
        cajasDisponibles.release();
    }

}
