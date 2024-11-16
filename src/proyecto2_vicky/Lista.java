/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class Lista {

    public Nodo pFirst;
    public Nodo pLast;
    public int size;

    public Lista(){
        this.pFirst = this.pLast = null;
        this.size = 0;
    }
    
    public void insertar(Nodo integrante){
        if(this.isEmpty()){
            this.pFirst = this.pLast = integrante;
        }else{
            this.pLast.pNext = integrante;
            this.pLast = integrante;
        }
        this.size++;
    }

    public boolean isEmpty(){
        return this.pFirst == null;
    }


    public Nodo buscar(String mote_integrante){
        if(this.isEmpty()){
            return null;
        }else{
             Nodo aux = this.pFirst;
             while(aux!= null &&!aux.mote.toLowerCase().equals(mote_integrante.toLowerCase())){
                aux = aux.pNext;
             }
             return aux;
        }
    }
}
