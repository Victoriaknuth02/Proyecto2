/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class Nodo {
    String nombre;
    String numeral;
    Nodo padre;
    String mote;
    String titulo;
    String esposa;
    String ojos;
    String cabello;
    Nodo[] hijos;
    String notas;
    String destino;
    
    Nodo pNext;
    
    public Nodo(String ofHisName, Nodo padre, String ofEyes, String ofHair){
        this.numeral = ofHisName;
        this.padre = padre;
        this.ojos = ofEyes;
        this.cabello = ofHair;
        this.pNext = null;
    }
}
