package Hilos;


import java.util.Random;


import Transporte.ColectivoFolklorico;
import actividades.CentroCompras;
import actividades.Entrada;
import actividades.Mirador;
import actividades.Restaurant;
import actividades.Rio;
import actividades.ZonaDeEntrega;
import Transporte.Tren;
import static java.lang.Math.random;


public class Cliente implements Runnable {

    private Entrada e;
    private ColectivoFolklorico c;
    private CentroCompras shop;
    private Restaurant r[];
    private Mirador m;
    private Rio rio;
    private Random Random= new Random();
    private ZonaDeEntrega z;
    private Tren t;



    public Cliente (Restaurant r[],Entrada e, ColectivoFolklorico c, CentroCompras shop,  Mirador m, Rio rio, ZonaDeEntrega z, Tren t){
        
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
        System.out.println(Thread.currentThread().getName()+ " Se va del parque!");

    
    }
  

    public void simular(int nro){
        try{
            Thread.sleep(nro);

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
           nro=Random.nextInt(5);
        }
        
}
    
    private void faroMirador(){
      m.entrarALaFila();
      System.out.println(Thread.currentThread().getName()+": Estoy esperando en la fila");
      m.primeroDeLaFila();
      m.avisoEncargado();
      char tobogan= m.tirarsePorTobogan();
      System.out.println(Thread.currentThread().getName()+": Tirandose por el tobogan "+tobogan);
      this.simular(2000);
      System.out.println(Thread.currentThread().getName()+": Termino de tirarse por el tobogan "+tobogan);
      m.liberarTobogan(tobogan);
        System.out.println(Thread.currentThread().getName()+": Se fue del faro mirador");
    }
    
    private void restaurantep(){
        int indiceRestaurante = Random.nextInt(r.length);
        r[indiceRestaurante].ingresar();
        r[indiceRestaurante].consumirAlmuerzo();
        System.out.println(Thread.currentThread().getName()+ " consumio almuerzo en " +indiceRestaurante);
        r[indiceRestaurante].salirRestaurante();
        System.out.println(Thread.currentThread().getName()+" Termino de consumir su almuerzo en el resto "+indiceRestaurante);
        //y ahora la merienda
        indiceRestaurante = Random.nextInt(r.length);
        r[indiceRestaurante].ingresar();
        r[indiceRestaurante].consumirMerienda();
        System.out.println(Thread.currentThread().getName()+ " consumio merienda en " +indiceRestaurante);
        r[indiceRestaurante].salirRestaurante();
        System.out.println(Thread.currentThread().getName()+" Termino de consumir su merienda en el resto "+indiceRestaurante);
    }
    
    private void snorkelitos(){
         z.avisarEncargado();
        System.out.println(Thread.currentThread().getName() + " Entre a la zona de entrega para snorkel ilimitado y le aviso al encargado");
        z.hacerFilaPatasRana();
        z.hacerFilaSalvavidas();
        z.hacerFilaSnorkel();
        System.out.println(Thread.currentThread().getName()+" Esta disfrutando de la actividad snorkel ilimitado");
        this.simular(4000);
        z.salirDeLaPileta();
        System.out.println(Thread.currentThread().getName()+ " Se fue de la actividad de la pileta");
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
        
        System.out.println(Thread.currentThread().getName()+ " Se fue de la actividad carrera de gomones");
    }
}

    


    
    


