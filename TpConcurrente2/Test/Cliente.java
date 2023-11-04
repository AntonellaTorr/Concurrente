package Test;

import IngresarParque.*;
import Shop.*;
import java.util.Random;
import FaroMirador.*;
import Restaurant.Restaurant;
import Snorkel.*;


public class Cliente implements Runnable {

    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;
    private Restaurant r;
    private Mirador m;
    private CentroSnorkel s;


    public Cliente (Restaurant r,Entrada e, ColectivoFolklorico c, CentroCompras shop,  Mirador m, CentroSnorkel s){
        this.s=s;
        this.r=r;
        this.e=e;
        this.c=c;
        this.shop=shop;
        this.m=m;
    }


    public void run (){
        Random random= new Random();
     
        this.realizarNadoSnorkel();
        
    
    
    }
  

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    public void realizarNadoSnorkel() {

        s.pedirElementos();
        this.simula();
        s.devolverEquipo();


    }

  


}
