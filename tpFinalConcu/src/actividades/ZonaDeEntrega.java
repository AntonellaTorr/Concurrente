package actividades;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
Disfruta de Snorkel ilimitado
existe la posibilidad de realizar snorkel en una laguna, para lo cual es necesario 
adquirir previamente el equipo de snorkel, salvavidas y patas de ranas, que deberán 
ser devueltos al momento de finalizar la actividad. En el ingreso a la actividad hay 
un stand donde dos asistentes entregan el equipo mencionado. La única limitación en 
cuanto a capacidad es dada por la cantidad de equipos completos (snorkel, salvavidas y patas de rana) existentes.
*/

public class ZonaDeEntrega {

    private int cantClienteEsperando;
    private int cantEncargados;
    private int cantPatasDeRana, cantSnorkel, cantSalvavidas;
    private Lock lock = new ReentrantLock(true); // para darle fairness
    private Condition esperaEncargado = lock.newCondition();
    private Condition esperaCliente = lock.newCondition();
    private Condition esperaPorPatasDeRana = lock.newCondition();
    private Condition esperaPorSalvavidas = lock.newCondition();
    private Condition esperaPorSnorkel = lock.newCondition();

    public ZonaDeEntrega(int cantPatasDeRana, int cantSnorkel, int cantSalvavidas) {
        this.cantPatasDeRana = cantPatasDeRana;
        this.cantSalvavidas = cantSalvavidas;
        this.cantSnorkel = cantSnorkel;
        this.cantClienteEsperando = 0;
        this.cantEncargados = 2;
    }

    public void avisarEncargado() {
        lock.lock();
        cantClienteEsperando++;
        esperaEncargado.signal();
        while (cantEncargados <= 0) { // mientras no haya encargados espero
            try {
                esperaCliente.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaDeEntrega.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantEncargados--; // agarro el encargado para que me atienda (?
        lock.unlock();
    }

    public void esperarCliente() {
        lock.lock();
        while (cantClienteEsperando <= 0) // espero por un cliente
            try {
                esperaEncargado.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaDeEntrega.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println(Thread.currentThread().getName() + " hay un cliente esperando, lo atiendo");
        cantClienteEsperando--; // disminuyo la cantidad d clientes esperando
        lock.unlock();
    }

    public void hacerFilaPatasRana() {
        lock.lock();
        cantEncargados++;
        while (cantPatasDeRana <= 0) {
            try {
                esperaPorPatasDeRana.await();
            } catch (Exception e) {
            }
        }
        // me desperte porque hay patas de rana (ahora solo necesito que el encargado
        // "me las de"
        while (cantEncargados == 0) {
            try {
                esperaCliente.await();
            } catch (InterruptedException ex) {
            }
        }
        // ahora si
        cantEncargados--;
        cantPatasDeRana--;
        cantEncargados++;
        esperaCliente.signal();
        lock.unlock();
    }

    public void hacerFilaSalvavidas() {
        lock.lock();
        cantEncargados++;
        while (cantSalvavidas <= 0) {
            try {
                esperaPorSalvavidas.await();
            } catch (Exception e) {
            }
        }
        // me desperte porque hay patas de rana (ahora solo necesito que el encargado
        // "me las de"
        while (cantEncargados == 0) {
            try {
                esperaCliente.await();
            } catch (InterruptedException ex) {
            }
        }
        // ahora si
        cantEncargados--;
        cantSalvavidas--;
        cantEncargados++;
        esperaCliente.signal();
        lock.unlock();
    }

    public void hacerFilaSnorkel() {
        lock.lock();
        cantEncargados++;
        while (cantSnorkel <= 0) {
            try {
                esperaPorSnorkel.await();
            } catch (Exception e) {
            }
        }
        while (cantEncargados == 0) {
            try {
                esperaCliente.await();
            } catch (InterruptedException ex) {
            }
        }
        // ahora si
        cantEncargados--;
        cantSnorkel--;
        cantEncargados++;
        esperaCliente.signal();
        lock.unlock();
    }

    public void salirDeLaPileta() {
        lock.lock();
        cantPatasDeRana++;
        esperaPorPatasDeRana.signal();
        cantSnorkel++;
        esperaPorSnorkel.signal();
        cantSalvavidas++;
        esperaPorSalvavidas.signal();
        lock.unlock();
    }

}
