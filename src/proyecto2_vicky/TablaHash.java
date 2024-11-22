/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class TablaHash {

    public Lista[] integrantes;
    public int size;
    public int count;

    public TablaHash(int size) {
        this.size = size;
        this.count = 0;
        this.integrantes = new Lista[this.size];
        for (int i = 0; i < this.size; i++) {
            this.integrantes[i] = new Lista();
        }
    }

    public int hash(String mote_integrante) {
        int hash = 1;
        if (mote_integrante.length() > 0) {
            for (int i = 0; i < mote_integrante.length(); i++) {
                hash += hash * 31 + mote_integrante.charAt(i);
            }
        }
        return hash % this.size;
    }

    public void insertar(Nodo integrante, int modo) {
        int hash = -1;
        if (modo == 1) {
             hash = this.hash(integrante.nombre.toLowerCase());

        } else if (modo == 2) {
             hash = this.hash(integrante.mote.toLowerCase());

        } else if (modo == 3) {
             hash = this.hash(integrante.titulo.toLowerCase());

        }
        if (this.integrantes[hash].buscar(integrante.nombre.toLowerCase(), 1) != null && this.integrantes[hash].buscar(integrante.mote.toLowerCase(), 2) != null && this.integrantes[hash].buscar(integrante.titulo.toLowerCase(), 3) != null) {
            return;
        } else {
            this.integrantes[hash].insertar(integrante);
            this.count++;
        }
    }

    public Nodo buscar(String clave, int modo) {
        int hash = this.hash(clave.toLowerCase());
        if (modo == 1) {
            if (this.integrantes[hash].pFirst.nombre.toLowerCase().equals(clave.toLowerCase())) {
                return this.integrantes[hash].pFirst;
            } else {
                return this.integrantes[hash].buscar(clave, 1);
            }
        } else if (modo == 2) {
            if (this.integrantes[hash].pFirst.mote.toLowerCase().equals(clave.toLowerCase())) {
                return this.integrantes[hash].pFirst;
            } else {
                return this.integrantes[hash].buscar(clave, 2);
            }
        } else if (modo == 3) {
            if (this.integrantes[hash].pFirst.titulo.toLowerCase().equals(clave.toLowerCase())) {
                return this.integrantes[hash].pFirst;
            } else {
                return this.integrantes[hash].buscar(clave, 3);
            }
        }
        return null;

    }

}
