package actividades;
import java.util.concurrent.Semaphore;

/*El ingreso al parque está indicado a través del paso de k molinetes. Una vez ingresado,
el visitante puede optar por ir al shop o disfrutar de las actividades del parque. */

public class Molinete {
    private Semaphore siendoUsado;

    public Molinete (){
        siendoUsado= new Semaphore(1);
    }

    public boolean molineteLibre (){
        return siendoUsado.tryAcquire();
    }
    public void liberarMolinete(){
        siendoUsado.release();
    }
    
    public void esperarPorMolinete(){
        try {
            siendoUsado.acquire();
        } catch (Exception e) {
        }
    }
}
