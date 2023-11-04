package Restaurant;

import java.util.Random;

public class Cocinero implements Runnable {
    private Restaurant r;
    private char e;

    public Cocinero (Restaurant r, char e){
        this.r=r;
        this.e=e;

    }

    public void run (){
        Random ran= new Random();
        while (true){
            if (e=='a'){
                r.cocinarAlmuerzos();
            }
            else{
                r.cocinarMeriendas();
            }
            this.simular();
           

        }
    }
    public void simular (){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

