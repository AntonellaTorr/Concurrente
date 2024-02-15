package Transporte;

public class Tren {

    private int cantAdentro; //para controlar que no se suban clientes de mas
    private int capacidadMaxima;
    private boolean enPuerta; //para que no intenten subirse los clientes en cualquier lado
    private boolean bajarse; //para avisar a los clientes que ya se llego al parque
    private int timeout; // Tiempo de espera en milisegundos

    public Tren() {
        this.cantAdentro = 0;
        this.capacidadMaxima = 25; //Lo dice el enunciado
        this.bajarse = false;
        this.enPuerta = true;
        this.timeout = 40000;
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

    public synchronized void bajarse() {
        //metodo utilzado por el cliente
        while (!bajarse) {
            try {
                this.wait(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (timeout > 0 && cantAdentro < capacidadMaxima) {
                System.out.println(Thread.currentThread().getName() + " Se bajo del tren porque no se completo el cupo");
                return;
            }
        }
        cantAdentro--;
        this.notifyAll();
    
    }

    public synchronized void avisarLlegadaDestino() {
        //metodo utilizado por el encargado
        bajarse = true;
        this.notifyAll();
    }

    public synchronized void posicionarseEnPuerta() {
        //metodo utilizado por el encargado
        bajarse = false;
        enPuerta = true;
        this.notifyAll();
    }

    public synchronized void iniciarViaje() {
        //metodo utilizado por el encargado
        while (cantAdentro < capacidadMaxima) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enPuerta = false;
        this.notifyAll(); // Se notifica a los clientes que el viaje ha comenzado
    }
}
