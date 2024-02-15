/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;


import Hilos.Cliente;
import Hilos.Cocinero;
import Hilos.EmpleadoColectivoFolklorico;
import Hilos.EmpleadoTrenGomones;
import Hilos.EncargadoFaroMirador;
import Hilos.EncargadoSnorkel;
import Hilos.Reloj;
import Hilos.Reseteador;
import Hilos.ReseteadorBarreraDeSalida;
import Transporte.ColectivoFolklorico;
import Hilos.Gomon;
import Transporte.Tren;
import actividades.CentroCompras;
import actividades.Entrada;
import actividades.Mirador;
import actividades.Restaurant;
import actividades.Rio;
import actividades.ZonaDeEntrega;
import utiles.TecladoIn;


public class Main {
 public static void main(String[] args) {
     
     //CREACION E INICIALIZACION DE LAS ACTIVIDADES
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("DAREMOS INICIO A LA CREACION E INICIALIZACION DE LAS ACTIVIDADES DEL PARQUE");
        System.out.println("---------------------------------------------------------------------------");
        
        //CLASES CON CAPACIDADES DEFINIDAS EN EL ENUNCIADO
        Tren trencito= new Tren();
        ColectivoFolklorico colectivoF= new ColectivoFolklorico();
        CentroCompras centroC = new CentroCompras();
        Restaurant restos[] = new Restaurant [3];
        
        //ACTIVIDAD: Entrada al Parque
        System.out.println("Ingrese la cantidad de molinetes que debera tener la entrada al parque");
        int nroMolinetes= TecladoIn.readLineInt();
        Entrada entradita= new Entrada(nroMolinetes);
        Reloj relojito = new Reloj (entradita);
        
        //ACTIVIDAD: FARO-MIRADOR
        System.out.println("Ingrese la cantidad de escalones que tendra la escalera caracol para la actividad: Faro-Mirador");
        int nroEscaleras= TecladoIn.readLineInt();
        Mirador miradorcito = new Mirador(nroEscaleras);
        
        //ACTIVIDAD: RESTAURANT
        System.out.println("Ingrese la capacidad de cada restaurantes (todos tendran la misma)");
        int capacRes=TecladoIn.readLineInt();
        for (int i=0; i<restos.length; i++){
            restos[i]= new Restaurant (capacRes, i);
        }
        
        //ACTIVIDAD: SNORKEL ILIMITADO
        System.out.println("Ingrese la cantidad de patas de rana para la actividad Snorkel Ilimitado");
        int cantPatasDeRana=TecladoIn.readLineInt();
        System.out.println("Ingrese la cantidad de snorkels para la actividad Snorkel Ilimitado");
        int cantSnorkels=TecladoIn.readLineInt();
        System.out.println("Ingrese la cantidad de salvavidas para la actividad Snorkel Ilimitado");
        int cantSalvavidas=TecladoIn.readLineInt();
        ZonaDeEntrega zona = new ZonaDeEntrega(cantPatasDeRana,cantSnorkels,cantSalvavidas);
        
       //ACTIVIDAD: CARRERA DE GOMONES 
         System.out.println("ingrese la cantidad de gomones simples;");
         int cantGomonSim= TecladoIn.readLineInt();
         System.out.println("ingrese la cantidad de gomones dobles");
         int cantGomonDob= TecladoIn.readLineInt();
         System.out.println("ingrese la cantidad de gomones que se pueden tirar por carrera");
         int cantCapCarrera= TecladoIn.readLineInt();
         Rio rio= new Rio(cantGomonSim, cantGomonDob, cantCapCarrera);

         
         //CREACION DEL PERSONAL 
         
     
         EncargadoFaroMirador empleadoFaro = new EncargadoFaroMirador(miradorcito);
         EmpleadoColectivoFolklorico empleadoCole = new EmpleadoColectivoFolklorico(colectivoF);
         EmpleadoTrenGomones empleadoTren = new EmpleadoTrenGomones(trencito);
         EncargadoSnorkel []empleadoSnorkel = new EncargadoSnorkel[2];
         Reloj r = new Reloj(entradita);
         Reseteador reset = new Reseteador(rio);
         ReseteadorBarreraDeSalida resetSalida = new ReseteadorBarreraDeSalida(rio);
         
         System.out.println("Ingrese la cantidad de Clientes");
         int cantClientes = TecladoIn.readLineInt();
         
         System.out.println("");
         System.out.println("---------------------------");
         System.out.println("---------------------------");
         System.out.println("---------------------------");
         System.out.println("");
         
         
         //CREACION E INICIALIZACION DE HILOS
         Thread thCocineros[], thClientes [], thGomones[], thEmpleadoSnorkel[];
         Thread thEmpleadoColectivo, thEmpleadoFaro, thEmpleadoTren, thReloj, thReset, thResetSalida;
         
         thCocineros= new Thread [6];
         thClientes = new Thread [cantClientes];
         thEmpleadoColectivo = new Thread(empleadoCole, "Empleado Cole");
         thEmpleadoFaro = new Thread (empleadoFaro, "Empleado Faro");
         thEmpleadoTren = new Thread (empleadoTren, "Empleado Tren");
         thEmpleadoSnorkel= new Thread [empleadoSnorkel.length];
         thReloj = new Thread(relojito, "Reloj");
         thReset = new Thread (reset, "Reset");
         thResetSalida = new Thread (resetSalida, "Reset Salida");
         thGomones = new Thread[(cantGomonDob+cantGomonSim)];
         
         thEmpleadoColectivo.start();
         thEmpleadoFaro.start();
         thEmpleadoTren.start();
         thReset.start();
         thResetSalida.start();
         thReloj.start();
         
          for (int i = 0; i < thEmpleadoSnorkel.length; i++) {
            thEmpleadoSnorkel[i]= new Thread(new EncargadoSnorkel(zona), "Encargado "+i);
            thEmpleadoSnorkel[i].start();
        }
         
       
         for (int i = 0; i < thCocineros.length; i += 2) {
            int restauranteAsignado = (i / 2);
            thCocineros[i]= new Thread(new Cocinero(restos[restauranteAsignado],'a'), "Cocinero"+i);
            thCocineros[i+1]= new Thread(new Cocinero(restos[restauranteAsignado],'m'), "Cocinero"+i+1);
            thCocineros[i].start();
            thCocineros[i+1].start();
        }
         
         
       int k=0;
       for (int i=0; i<cantGomonSim;i++){
           thGomones[k]= new Thread (new Gomon(1,rio) );
           k++;
       }
       
       for (int i=0; i<cantGomonDob;i++){
           thGomones[k]= new Thread (new Gomon(2,rio));
           k++;
       }
       
       for (int i=0; i<thGomones.length;i++){
           thGomones[i].start();
       }
       
       
       
        for (int j=0; j< cantClientes; j++){
            thClientes [j]= new Thread(new Cliente(restos, entradita, colectivoF, centroC, miradorcito, rio, zona, trencito), "Cliente "+j);
            thClientes[j].start();
        }
        
        
        
 }
}
       