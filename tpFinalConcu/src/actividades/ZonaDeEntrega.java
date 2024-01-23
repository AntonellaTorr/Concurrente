/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package actividades;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author male_
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
        System.out.println(Thread.currentThread().getName() + " Entre a la zona de entrega y le aviso al encargado");
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
        System.out.println("-------");
        System.out.println(Thread.currentThread().getName() + " Voy a esperar por las mf patas de runnable");
        System.out.println("------");
        while (cantPatasDeRana <= 0) {
            try {
                esperaPorPatasDeRana.await();
            } catch (Exception e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + "supuestamente hay patas de rana : " + cantPatasDeRana);
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
        System.out.println(Thread.currentThread().getName() + "CONSEGUI PTAS D RANA");
        lock.unlock();
    }

    public void hacerFilaSalvavidas() {
        lock.lock();
        cantEncargados++;
        System.out.println("-------");
        System.out.println(Thread.currentThread().getName() + " Voy a esperar por un salvavidas");
        System.out.println("------");
        while (cantSalvavidas <= 0) {
            try {
                esperaPorSalvavidas.await();
            } catch (Exception e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + "supuestamente hay salvavidas : " + cantSalvavidas);
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
        System.out.println(Thread.currentThread().getName() + "CONSEGUI Salvdaa");
        lock.unlock();
    }

    public void hacerFilaSnorkel() {
        lock.lock();
        cantEncargados++;
        System.out.println("-------");
        System.out.println(Thread.currentThread().getName() + " Voy a esperar por un snorkel");
        System.out.println("------");
        while (cantSnorkel <= 0) {
            try {
                esperaPorSnorkel.await();
            } catch (Exception e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + "supuestamente hay snorkel: " + cantSnorkel);
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
        cantSnorkel--;
        cantEncargados++;
        esperaCliente.signal();
        System.out.println(Thread.currentThread().getName() + "CONSEGUI SKNOREL");
        lock.unlock();
    }

    public void salirDeLaPileta() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " ME VOY PUTOS");
        cantPatasDeRana++;
        esperaPorPatasDeRana.signal();
        cantSnorkel++;
        esperaPorSnorkel.signal();
        cantSalvavidas++;
        esperaPorSalvavidas.signal();
        lock.unlock();
    }

}
