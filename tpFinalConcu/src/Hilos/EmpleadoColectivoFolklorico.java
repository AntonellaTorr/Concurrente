package Hilos;

import Transporte.ColectivoFolklorico;

/*
Al parque se puede acceder en forma particular o por tour, en el caso del tour, se
trasladan a través de colectivos folklóricos con una capacidad no mayor a 25 personas,
que llegan a un estacionamiento destinado para tal fin
*/

public class EmpleadoColectivoFolklorico  implements Runnable{
    
        ColectivoFolklorico c;
        
        public EmpleadoColectivoFolklorico(ColectivoFolklorico c){
            this.c=c;
        }
        
        public void run(){
            while(true){
            c.posicionarseEnPuerta();
            c.iniciarViaje();
            this.simulaViaje();
            c.avisarLlegadaDestino();
            this.simulaViaje();

            }
        }
        
        private void simulaViaje(){
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }
        
    }

