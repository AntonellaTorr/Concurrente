package Hilos;

import actividades.Restaurant;
import java.util.Random;


/*
Restaurante:
En el pago del acceso al Parque se encuentra incluido el almuerzo y la
merienda. Existen tres restaurantes, pero solamente se puede consumir un almuerzo
y una merienda en cualquiera de ellos. Puede tomar el almuerzo en un restaurante y
la merienda en otro. Los restaurantes tienen capacidad limitada. Las personas son
atendidas en orden de llegada. Los restaurantes tienen habilitada una cola de espera.
*/

public class Cocinero implements Runnable {
    private Restaurant r;
    private char e;

    public Cocinero (Restaurant r, char e){
        this.r=r;
        this.e=e;

    }

    public void run (){
        while (true){
            if (e=='a'){
                r.cocinarAlmuerzos();
            }
            else{
                r.cocinarMeriendas();
            }
            this.simular(); //para simular el tiempo que le tomaria armar las 10 almuerzos / meriendas
           

        }
    }
    public void simular (){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

