package CarreraGomones;

public class Reseteador implements Runnable{

    private Rio r;

    public Reseteador(Rio r){
        this.r=r;
    }

    public void run(){
       
        r.resetearJuego();
       
    }
}
