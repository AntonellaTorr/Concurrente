package actividades;

import java.util.concurrent.Semaphore;

/*El ingreso al parque está indicado a través del paso de k molinetes. Una vez ingresado,
el visitante puede optar por ir al shop o disfrutar de las actividades del parque. */

public class Entrada {
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
     public static final String ANSI_RESET = "\u001B[0m";
    
   private Molinete[] molinetes; //cantidad que es ingresada por parametros
   private int horaActual;
   private Semaphore mutexHora;
   
   public Entrada(int cantMolinetes){
       molinetes= new Molinete [cantMolinetes];
       this.inicializarMolinetes();
       horaActual=9;
       mutexHora= new Semaphore(1);
  
   }
   public int getHora(){
    return horaActual;
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
       //si todavia es posible entrar al parque
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

    System.out.println(ANSI_GREEN_BACKGROUND+" -----------La hora es:" + horaActual+"-----------"+ANSI_RESET);
    mutexHora.release();
   }
    
    
}