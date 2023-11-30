package Test;
import CarreraGomones.Gomon;
import CarreraGomones.Rio;
import FaroMirador.Encargado;
import FaroMirador.Mirador;
import IngresarParque.ColectivoFolklorico;
import IngresarParque.EmpleadoColectivoFolklorico;
import IngresarParque.Entrada;
import IngresarParque.Reloj;
import Restaurant.Cocinero;
import Restaurant.Restaurant;
import Shop.CentroCompras;


public class Main {
 public static void main(String[] args) {
      
        Entrada e= new Entrada(4);
        CentroCompras centro= new CentroCompras();
    
        ColectivoFolklorico cole= new ColectivoFolklorico(25);
        Mirador m= new Mirador(5);
    


        Restaurant r= new Restaurant(10, 1);
        Thread cocinero1 = new Thread(new Cocinero(r, 'a'));
        Thread cocinero2= new Thread(new Cocinero(r, 'm'));


       Rio rio= new Rio(3, 5, 4);

        
     


        Thread []c= new Thread[20];
        Thread []g= new Thread[8];
        Thread empCole= new Thread(new EmpleadoColectivoFolklorico(cole));
        Thread en= new Thread(new Encargado(m));

        Thread reloj= new Thread(new Reloj(e));
        
        reloj.start();
       
       /* empCole.start();
  
       cocinero1.start();
       cocinero2.start();
*/
   
        for (int i=0; i<c.length;i++){
           c[i]= new Thread (new Cliente(r, e, cole, centro,m,rio), "Cliente "+i );


        }
        for (int i=0; i<c.length;i++){
            c[i].start();

        }
        
        for (int i=0; i<3;i++){
           g[i]= new Thread (new Gomon(1,rio) );

        }
       for (int i=3; i<8;i++){
           g[i]= new Thread (new Gomon(2,rio) );
       }

       for (int i=0; i<8;i++){
           g[i].start();
       }

  


        
 }   
}
