package Hilos;


import java.util.Random;


import Transporte.ColectivoFolklorico;
import actividades.CentroCompras;
import actividades.Entrada;
import actividades.Mirador;
import actividades.Restaurant;
import actividades.Rio;
import actividades.ZonaDeEntrega;
import Transporte.Tren
import static java.lang.Math.random;


public class Cliente implements Runnable {

    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;
    private Restaurant r;
    private Mirador m;
    private Rio rio;
    private Random Random= new Random();
    private ZonaDeEntrega z;
    private Tren t;



    public Cliente (Restaurant r,Entrada e, ColectivoFolklorico c, CentroCompras shop,  Mirador m, Rio rio, ZonaDeEntrega z, Tren t){
        
        this.r=r;
        this.e=e;
        this.c=c;
        this.shop=shop;
        this.m=m;
        this.rio=rio;
        this.z = z;
        this.t=t;
    }


    public void run (){
        this.irHastaElParque();
        System.out.println(Thread.currentThread().getName()+" Ya entro al parque");
        this.entradaAlParque();
        this.disfrutarActividades();
        System.out.println(Thread.currentThread().getName()+ "Se va del parque!");

    
    }
  

    public void simula(){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
    }

    private void irHastaElParque(){
        if(Random.nextInt(2)==0){
            System.out.println(Thread.currentThread().getName()+" Accede al parque en un tour");
            c.subirse();
            c.bajarse();
        }else
            System.out.println(Thread.currentThread().getName()+" Va caminando hasta el parque");
    }
    
    private void entradaAlParque() {
        int nroMolinete= e.entrarAlParque();
        e.entro(nroMolinete);
        if(Random.nextInt(2)==0){
            System.out.println(Thread.currentThread().getName()+" entro al shop y se compro algo");
            shop.irAPagar();
            shop.finalizarPago();
        }else
            System.out.println(Thread.currentThread().getName()+" Prefirio no entrar al shop");
    }

    

    private void disfrutarActividades(){
        //El cliente accedera a todas las actividades que quiera hasta o que cierre el parque o weno
        int nro=Random.nextInt(5);
       while(nro!=4){
           switch(nro){
               case 0:
                     System.out.println(Thread.currentThread().getName()+ "Participo de la actividad Faro-Mirador");
                     this.faroMirador();
                     break;
               case 1:
                   System.out.println(Thread.currentThread().getName()+ "Fue al restaurante");
                   this.restaurantep();
                   break;
               case 2:
                   System.out.println(Thread.currentThread().getName()+" Participo de la carrera de Gomones");
                   this.carrerita();
                   break;
               case 3:
                   System.out.println(Thread.currentThread().getName()+ "Participo de la actividad Snorkel Ilimitado");
                   this.snorkelitos();
                   break;
           }
           nro=Random.nextInt(4);
        }
        
}
    
    private void faroMirador(){
      m.entrarALaFila();
      m.primeroDeLaFila();
      m.avisoEncargado();
      char tobogan= m.tirarsePorTobogan();
      System.out.println(Thread.currentThread().getName()+": Tirandose por el tobogan "+tobogan);
      this.simular();
      System.out.println(Thread.currentThread().getName()+": Termino de tirarse por el tobogan "+tobogan);
      m.liberarTobogan(tobogan);
    }
    
    private void restaurantep(){
        r.ingresar();
        r.consumirAlmuerzo();
        r.consumirMerienda();
        r.salirRestaurante();
    }
    
    private void snorkelitos(){
         z.avisarEncargado();
        z.hacerFilaPatasRana();
        z.hacerFilaSalvavidas();
        z.hacerFilaSnorkel();
        System.out.println(Thread.currentThread().getName()+"Entre a la piletaaa");
        this.simular()*2;
        z.salirDeLaPileta();
        }
    
    
    private void carrerita(){
        if(Random.nextInt(2)==0){
             t.subirse();
             t.bajarse();
        }else{
            System.out.println(Thread.currentThread().getName()+ "Subio hasta la actividad de gomones en bici");
        }
        int nroBolso=Random.nextInt(2);
        if(nroBolso==0){
             System.out.println(Thread.currentThread().getName()+" Dejo su bolso con pertenencias, debe pasar a buscarlo al final de la act");
        }
         Random r = new Random();
            if (r.nextInt(2) % 2 == 0)
                 rio.liberarGomon(1);
            else
                rio.liberarGomon(2);
            
        if(nroBolso==0)
            System.out.println(Thread.currentThread().getName()+" Paso a buscar su bolso con sus pertenencias");
    }

}
    
    


