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
private int cantAdentro; //para controlar que no se suban mas personas de la cantidad establecida
private int capacidadMaxima;
private boolean enPuerta; //para que los clientes no se suban en cualquier lado
private boolean bajarse; //para avisarle a los clientes que ya llegamos al inicio de la actividad


public Tren (){
    this.cantAdentro=0;
    this.capacidadMaxima=15; //lo dice el enunciado
    this.bajarse=false;
    this.enPuerta=true;
}


public synchronized void subirse(){
    //metodo utilizado por el cliente
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
}

public synchronized  void bajarse(){
    //metodo utilizado por el cliente
    while (!bajarse){
         try {
            this.wait();
        } catch (InterruptedException e) {
          
            e.printStackTrace();
        }
    }
    cantAdentro--;
    this.notifyAll();
}

public synchronized void avisarLlegadaDestino(){
    bajarse=true;
    this.notifyAll();
}

public synchronized void posicionarseEnPuerta(){
    bajarse=false;
    enPuerta=true;
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
    }


}


    

