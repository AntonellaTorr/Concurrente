package Transporte;

/* Carrera de gomones por el río
esta actividad permite que los visitantes bajen por el rio, que se encuentra rodeado de manglares, 
compitiendo entre ellos. Para ello es necesario llegar hasta el inicio de la actividad 
a través de bicicletas que se prestan es un stand de bicicletas, o a través de un tren interno 
que tiene una capacidad de 15 personas como máximo. Al llegar al inicio del recorrido cada
persona dispondrá de un bolso con llave, en donde podrá guardar todas las pertenencias que no quiera
mojar. Los bolsos están identificados con un número al igual que la llave, los bolsos
serán llevados en una camioneta, hasta el final del recorrido en donde podrán ser
retirados por el visitante. Para bajar se utilizan gomones, individuales o con
capacidad para 2 personas. La cantidad de gomones de cada tipo es limitada. Para
habilitar una largada es necesario que haya h gomones listos para salir, no importa
el tipo.
*/

public class Tren {
private int cantAdentro;
private int capacidadMaxima;
private boolean enPuerta;
private boolean bajarse;


public Tren (){
    this.cantAdentro=0;
    this.capacidadMaxima=15;
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


    

