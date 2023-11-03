
import IngresarParque.*;
import Shop.*;
import java.util.Random;
import FaroMirador.*;


public class Cliente implements Runnable {

    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;
  
    private Mirador m;


    public Cliente (Entrada e, ColectivoFolklorico c, CentroCompras shop,  Mirador m){

        this.e=e;
        this.c=c;
        this.shop=shop;
        this.m=m;
    }


    public void run (){
        c.subirse();
        c.bajarse();
    
    
    }
  

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

  


}
