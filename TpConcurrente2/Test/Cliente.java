package Test;

import IngresarParque.*;
import Shop.*;
import java.util.Random;
import FaroMirador.*;
import Restaurant.Restaurant;
import CarreraGomones.*;


public class Cliente implements Runnable {

    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;
    private Restaurant r;
    private Mirador m;
    private Rio rio;
   


    public Cliente (Restaurant r,Entrada e, ColectivoFolklorico c, CentroCompras shop,  Mirador m, Rio rio){
        
        this.r=r;
        this.e=e;
        this.c=c;
        this.shop=shop;
        this.m=m;
        this.rio=rio;
    }


    public void run (){
        Random random= new Random();
        
        rio.liberarGomon();
        rio.bajarse();
    
    
    }
  

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    public void realizarNadoSnorkel() {



    }

  


}
