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

    public Lista[] archivosResumen;
    public int size;
    public int count;

    public TablaHash(int size) {
        this.size = size;
        this.count = 0;
        this.archivosResumen = new Lista[this.size];
        for (int i = 0; i < this.size; i++) {
            this.archivosResumen[i] = new Lista();
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

    public void insertar(Nodo integrante) {
        int hash = this.hash(integrante.mote.toLowerCase());
        if (this.archivosResumen[hash].buscar(integrante.mote.toLowerCase()) != null) {
            return;
        }
        else {
            this.archivosResumen[hash].insertar(integrante);
            this.count++;
        }
    }

    public Nodo buscarPorTitulo(String mote_integrante) {
        int hash = this.hash(mote_integrante.toLowerCase());
        if (this.archivosResumen[hash].pFirst.mote.toLowerCase().equals(mote_integrante.toLowerCase())) {
            return this.archivosResumen[hash].pFirst;
        } else {
            return this.archivosResumen[hash].buscar(mote_integrante);
        }
    }

}
