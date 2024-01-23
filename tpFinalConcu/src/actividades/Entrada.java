package actividades;

import java.util.concurrent.Semaphore;

public class Entrada {
   private Molinete[] molinetes;
   private int horaActual;
   private Semaphore mutexHora;
   
   public Entrada(int cantMolinetes){
       molinetes= new Molinete [cantMolinetes];
       this.inicializarMolinetes();
       horaActual=9;
       mutexHora= new Semaphore(1);
  
   }
   
   public int entrarAlParque(){
       boolean entro=false;
       int molineteAEsperar=0, i;
       i=0;
       try {
        mutexHora.acquire();
        } catch (InterruptedException e) {
        e.printStackTrace();
        }

       if(horaActual>=9 && horaActual<=17){
            mutexHora.release();
            //Busca un molinete libre
            while(i<molinetes.length && !entro ){
                if(molinetes[i].molineteLibre()){
                    //si lo encuentra
                    molineteAEsperar=i;
                    entro=true;
                }
                else
                    i++;
            }
            
            if(!entro){
                //Si no habia ningun molinete libre, simulamos que hace la fila en alguno
                molineteAEsperar= (int) (Math.random()*(molinetes.length));
                molinetes[molineteAEsperar].esperarPorMolinete();
            }
           
        }
        else{
            mutexHora.release();
            molineteAEsperar=-1;
        }
        return molineteAEsperar;
   }
   
   public void entro(int molineteQueLeToco){
       molinetes[molineteQueLeToco].liberarMolinete();
   }
   
   private void inicializarMolinetes(){
       for (int i = 0; i < molinetes.length; i++) {
           molinetes[i]= new Molinete();
       }
   }
   
   public void pasarHora(){
    try {
        mutexHora.acquire();
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
       if(horaActual>=23)
           horaActual=0;
       else
           horaActual++;

    mutexHora.release();
   }
    
    
}
