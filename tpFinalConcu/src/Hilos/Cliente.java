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
        boolean almuerzoNoConsumido=true, meriendaNoConsumida=true;
       while(nro!=5 && e.getHora()<18 && e.getHora()>=9){
           switch(nro){
               case 0:
                     System.out.println(Thread.currentThread().getName()+ " Decidio que ira a la actividad Faro-Mirador");
                     this.faroMirador();
                     break;
               case 1:
                   if(almuerzoNoConsumido){ //para que solamente puedan ir una vez a almorzar 
                        System.out.println(Thread.currentThread().getName()+ ": Decidio que ira a almorzar");
                        this.almuerzo();
                        almuerzoNoConsumido=false;
                   }
                   break;
               case 2:
                    if(!almuerzoNoConsumido && meriendaNoConsumida){ //si ya consumio el almuerzo y no la merienda 
                        System.out.println(Thread.currentThread().getName()+ ": Decidio que ira a merendar");
                        this.merienda();
                        meriendaNoConsumida=false;
                    }
                   break;
               case 3:
                   System.out.println(Thread.currentThread().getName()+": Decidio que ira a la carrera de Gomones");
                   this.carrerita();
                   break;
               case 4:
                   System.out.println(Thread.currentThread().getName()+ ": Decidio que ira a la actividad Snorkel Ilimitado");
                   this.snorkelitos();
                   break;
            

           }
           nro=Random.nextInt(5);
        }
        
}
    
    private void faroMirador(){
      m.entrarALaFila();
      System.out.println(Thread.currentThread().getName()+": Estoy esperando en la fila del Faro-Mirador");
      m.primeroDeLaFila();
      m.avisoEncargado();
      char tobogan= m.tirarsePorTobogan();
      System.out.println(Thread.currentThread().getName()+": Tirandose por el tobogan "+tobogan);
      this.simular(2000);
      System.out.println(Thread.currentThread().getName()+": Termino de tirarse por el tobogan "+tobogan);
      m.liberarTobogan(tobogan);
      System.out.println(Thread.currentThread().getName()+": Se fue del faro mirador");
    }
    
    private void almuerzo(){
        int indiceRestaurante = Random.nextInt(r.length); //Para decidir a que restaurante ira
        r[indiceRestaurante].ingresar();
        r[indiceRestaurante].consumirAlmuerzo();
        System.out.println(Thread.currentThread().getName()+ " consumira su almuerzo en el resto" +indiceRestaurante);
        this.simular(1000);
        r[indiceRestaurante].salirRestaurante();
        System.out.println(Thread.currentThread().getName()+" Termino de consumir su almuerzo en el resto "+indiceRestaurante);

      
    }
    private void merienda (){
          int indiceRestaurante = Random.nextInt(r.length); //Para decidir a que restaurante ira
          r[indiceRestaurante].ingresar();
          r[indiceRestaurante].consumirMerienda();
          System.out.println(Thread.currentThread().getName()+ " consumira su merienda en el resto " +indiceRestaurante);
          this.simular(1000);
          r[indiceRestaurante].salirRestaurante();
          System.out.println(Thread.currentThread().getName()+" Termino de consumir su merienda en el resto "+indiceRestaurante);
    }    
    private void snorkelitos(){
         z.avisarEncargado();
        System.out.println(Thread.currentThread().getName() + ": Entre a la zona de entrega para snorkel ilimitado y le aviso al encargado");
        z.hacerFilaPatasRana();
        z.hacerFilaSalvavidas();
        z.hacerFilaSnorkel();
        System.out.println(Thread.currentThread().getName()+": Esta disfrutando de la actividad snorkel ilimitado");
        this.simular(4000);
        z.salirDeLaPileta();
        System.out.println(Thread.currentThread().getName()+ ": Se fue de la actividad de snorkel ilimitado");
        }
    
    
    private void carrerita(){
        if(Random.nextInt(2)==0){ 
            System.out.println(Thread.currentThread().getName()+": Ira hasta el inicio de la carrera de gomones en Tren");
            //debe ir en tren
             t.subirse();
             t.bajarse();
        }else{
            System.out.println(Thread.currentThread().getName()+ ": Ira hasta la actividad de gomones en bici");
            this.simular(1000);
        }
        System.out.println(Thread.currentThread().getName()+ ": Llego al inicio de la actividad de gomones");
        
        int nroBolso=Random.nextInt(2); //Para decidir si deja o no el bolso
        if(nroBolso==0){
             System.out.println(Thread.currentThread().getName()+": Dejo su bolso con pertenencias, debe pasar a buscarlo al final de la act");
        }
        
        //Decidiremos si se sube a un gomon simple (1) o uno doble (2)
         Random r = new Random();
            if (r.nextInt(2) % 2 == 0)
                 rio.liberarGomon(1);
            else
                rio.liberarGomon(2);
            
         rio.bajarse();

         if(nroBolso==0) //Si habia dejado su bolso con pertenencias debe buscarlo
            System.out.println(Thread.currentThread().getName()+": Paso a buscar su bolso con sus pertenencias");
        
        System.out.println(Thread.currentThread().getName()+ ": Se fue de la actividad carrera de gomones");
    }
}

    


    
    

