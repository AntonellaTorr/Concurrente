import FaroMirador.Encargado;
import FaroMirador.Mirador;
import IngresarParque.ColectivoFolklorico;
import IngresarParque.EmpleadoColectivoFolklorico;
import IngresarParque.Entrada;
import IngresarParque.Reloj;
import Shop.CentroCompras;

public class Main {
 public static void main(String[] args) {
      
        Entrada e= new Entrada(4);
        CentroCompras centro= new CentroCompras();
    
        ColectivoFolklorico cole= new ColectivoFolklorico(25);
        Mirador m= new Mirador(5);
  

        
     


        Thread []c= new Thread[50];
        Thread empCole= new Thread(new EmpleadoColectivoFolklorico(cole));
        Thread en= new Thread(new Encargado(m));

        Thread reloj= new Thread(new Reloj(e));
        reloj.start();
       
        empCole.start();
  


        for (int i=0; i<c.length;i++){
            c[i]= new Thread (new Cliente( e, cole, centro,m), "Cliente "+i );


        }
        for (int i=0; i<c.length;i++){
            c[i].start();

        }

        
 }   
}
