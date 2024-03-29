package Hilos;

import actividades.Mirador;

/*
Faro-Mirador con vista a 40 m de altura y descenso en tobogán:
Admira desde lo alto todo el esplendor de una maravilla natural y desciende en tobogán hasta una
pileta. Para acceder al tobogán es necesario subir por una escalera caracol, que tiene
capacidad para n personas. Al llegar a la cima nos encontraremos con dos toboganes
para descender, la elección del tobogán es realizada por un administrador de cola
que indica que persona de la fila va a un tobogán y cuál va al otro. Es importante
destacar que una persona no se tira por el tobogán hasta que la anterior no haya
llegado a la pileta, es decir, sobre cada tobogán siempre hay a lo sumo una persona.
*/

public class EncargadoFaroMirador implements Runnable {
    Mirador m;
    
    public EncargadoFaroMirador(Mirador m){
        this.m=m;
    }
    
    public void run(){
        while(true){
            m.buscarToboganyLiberarPuedeTirarse();
        }
            
    }
    
}
