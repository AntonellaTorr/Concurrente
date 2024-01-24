package Transporte;

/*
Al parque se puede acceder en forma particular o por tour, en el caso del tour, se
trasladan a través de colectivos folklóricos con una capacidad no mayor a 25 personas,
que llegan a un estacionamiento destinado para tal fin
*/

public class ColectivoFolklorico {
private int cantAdentro; //para controlar que no se suban clientes de mas
private int capacidadMaxima;
private boolean enPuerta; //para que no intenten subirse los clientes en cualquier lado
private boolean bajarse; //para avisar a los clientes que ya se llego al parque


public ColectivoFolklorico (){
    this.cantAdentro=0;
    this.capacidadMaxima=25; //Lo dice el enunciado
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
    //metodo utilzado por el cliente
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
    //metodo utilizado por el encargado
    bajarse=true;
    this.notifyAll();
}

public synchronized void posicionarseEnPuerta(){
    //metodo utilizado por el encargado
    bajarse=false;
    enPuerta=true;
    this.notifyAll();

}

public synchronized void iniciarViaje(){
    //metodo utilizado por el encargado
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


    

