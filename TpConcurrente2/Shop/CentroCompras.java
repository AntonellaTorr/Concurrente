package Shop;

import java.util.concurrent.Semaphore;

public class CentroCompras {
    private Semaphore cajasDisponibles= new Semaphore(2);

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
