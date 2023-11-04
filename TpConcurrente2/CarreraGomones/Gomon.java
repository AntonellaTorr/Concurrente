package CarreraGomones;

public class Gomon implements Runnable {
    private int tipo;
    private Rio r;

    public Gomon (int tipo, Rio r){
        this.tipo=tipo;
        this.r=r;    
    }
    public void run (){
        r.correrCarrera(tipo);
        this.simular();
        r.finalizarCarrera();
    }
    
    public void simular (){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
        
    }
}
