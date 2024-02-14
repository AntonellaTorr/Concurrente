package Hilos;
import actividades.Rio;

/* Carrera de gomones por el río
esta actividad permite que los visitantes bajen por el rio, que se encuentra rodeado de manglares, 
compitiendo entre ellos. Para ello es necesario llegar hasta el inicio de la actividad 
a través de bicicletas que se prestan es un stand de bicicletas, o a través de un tren interno 
que tiene una capacidad de 15 personas como máximo. Al llegar al inicio del recorrido cada
persona dispondrá de un bolso con llave, en donde podrá guardar todas las pertenencias que no quiera
mojar. Los bolsos están identificados con un número al igual que la llave, los bolsos
serán llevados en una camioneta, hasta el final del recorrido en donde podrán ser
retirados por el visitante. Para bajar se utilizan gomones, individuales o con
capacidad para 2 personas. La cantidad de gomones de cada tipo es limitada. Para
habilitar una largada es necesario que haya h gomones listos para salir, no importa
el tipo.
*/



public class Gomon implements Runnable {
    private int tipo;
    private Rio r;

    public Gomon (int tipo, Rio r){
        this.tipo=tipo;
        this.r=r;    
    }
    
  
            
    public void run (){
        while(true){
        r.correrCarrera(tipo);
        this.simular();
        r.finalizarCarrera();
    }
    }
    
    public void simular (){
        try{
            Thread.sleep(100);

        }catch (Exception e){
        
        }
        
    }
}