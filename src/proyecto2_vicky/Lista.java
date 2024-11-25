/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
/**
 * La clase {@code Lista} representa una lista enlazada simple de nodos.
 * Proporciona métodos para insertar nodos, buscar nodos y verificar si la lista está vacía.
 */
public class Lista {

    private Nodo pFirst; // Primer nodo de la lista
    private Nodo pLast;  // Último nodo de la lista
    private int size;    // Tamaño de la lista

    /**
     * Crea una nueva instancia de {@code Lista} inicializando la lista vacía.
     */
    public Lista() {
        this.pFirst = this.pLast = null;
        this.size = 0;
    }

    /**
     * Inserta un nodo al final de la lista.
     *
     * @param integrante el nodo a insertar.
     */
    public void insertar(Nodo integrante) {
        if (this.isEmpty()) {
            this.setpFirst(this.pLast = integrante);
        } else {
            this.pLast.setpNext(integrante);
            this.setpLast(integrante);
        }
        this.setSize(this.getSize() + 1);
    }
    
    /**
     * Inserta un nodo al final de la lista (sobrecarga).
     *
     * @param i el nodo a insertar.
     * @param a un parámetro adicional (no utilizado en esta implementación).
     */
    public void insertar(Nodo i, int a) {
        Nodo integrante = i;
        i.setpNext(null);
        if (this.isEmpty()) {
            this.setpFirst(this.pLast = i);
        } else {
            this.pLast.setpNext(i);
            this.setpLast(i);
        }
        this.setSize(this.getSize() + 1);
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return {@code true} si la lista está vacía, {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return this.getpFirst() == null;
    }

    /**
     * Busca un nodo en la lista según una clave y un modo de búsqueda.
     *
     * @param clave la clave a buscar.
     * @param modo  el modo de búsqueda (1: nombre y numeral, 2: mote, 3: título).
     * @return el nodo encontrado o {@code null} si no se encuentra.
     */
    public Nodo buscar(String clave, int modo) {
        if (this.isEmpty()) {
            return null;
        } else {
            Nodo aux = this.getpFirst();

            if (modo == 1) {
                String claves[] = clave.split(",");

                while (aux != null) {
                    if (aux.getNombre().trim().toLowerCase().equals(claves[0].trim().toLowerCase()) && claves[1].trim().toLowerCase().contains(aux.getNumeral().trim().toLowerCase())) {
                        return aux;
                    }
                    aux = aux.getpNext();
                }
                return aux;

            } else if (modo == 2) {
                while (aux != null && !aux.getMote().toLowerCase().equals(clave.toLowerCase())) {
                    aux = aux.getpNext();
                }
                return aux;
            } else if (modo == 3) {
                while (aux != null && !aux.getTitulo().toLowerCase().equals(clave.toLowerCase())) {
                    aux = aux.getpNext();
                }
                return aux;
            }
        }
        return null;
    }

    /**
     * @return el primer nodo de la lista.
     */
    public Nodo getpFirst() {
        return pFirst;
    }

    /**
     * Establece el primer nodo de la lista.
     *
     * @param pFirst el primer nodo a establecer.
     */
    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return el último nodo de la lista.
     */
    public Nodo getpLast() {
        return pLast;
    }

    /**
     * Establece el último nodo de la lista.
     *
     * @param pLast el último nodo a establecer.
     */
    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }

    /**
     * @return el tamaño de la lista.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la lista.
     *
     * @param size el tamaño a establecer.
     */
    public void setSize(int size) {
        this.size = size;
    }
}