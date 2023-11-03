package FaroMirador;

public class Encargado implements Runnable {
    Mirador m;
    
    public Encargado(Mirador m){
        this.m=m;
    }
    
    public void run(){
        while(true){
            m.buscarToboganyLiberarPuedeTirarse();
        }
            
    }
    
}
