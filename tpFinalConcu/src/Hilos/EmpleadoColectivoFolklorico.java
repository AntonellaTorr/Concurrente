package Hilos;

import Transporte.ColectivoFolklorico;

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

