package Transporte;


public class ColectivoFolklorico {
private int cantAdentro;
private int capacidadMaxima;
private boolean enPuerta;
private boolean bajarse;


public ColectivoFolklorico (int capacidadMaxima){
    this.cantAdentro=0;
    this.capacidadMaxima=capacidadMaxima;
    this.bajarse=false;
    this.enPuerta=true;
}


public synchronized void subirse(){
    //mientras que este lleno o no este en puerta espera
    while (cantAdentro>=capacidadMaxima || !enPuerta){
        try {
            this.wait();
        } catch (InterruptedException e) {
          
            e.printStackTrace();
        }
    }
    cantAdentro++;
    this.notifyAll();
    System.out.println("Ya me pude subir" +Thread.currentThread().getName() +"cantidad "+cantAdentro);
}

public synchronized  void bajarse(){
    while (!bajarse){
         try {
            this.wait();
        } catch (InterruptedException e) {
          
            e.printStackTrace();
        }
    }
    cantAdentro--;
    this.notifyAll();
    System.out.println("Ya me pude bajar" +Thread.currentThread().getName()+ "cantidad "+cantAdentro);
    
}

public synchronized void avisarLlegadaDestino(){
    bajarse=true;
    System.out.println("--------------COLECTIVO LLEGO A DESTINO---------");
    this.notifyAll();
}

public synchronized void posicionarseEnPuerta(){
    bajarse=false;
    enPuerta=true;
    System.out.println("--------------COLECTIVO EN PUERTA---------");
    this.notifyAll();

}

public synchronized void iniciarViaje(){
    while(cantAdentro<capacidadMaxima){
        try {
            this.wait();
        } catch (InterruptedException e) {
          
            e.printStackTrace();
        }
    }
    enPuerta=false;
    System.out.println("--------------------------COLECTIVO INICIA VIAJE------------------------");


    }
}


    

