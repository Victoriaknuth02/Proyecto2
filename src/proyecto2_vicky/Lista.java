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

    private Nodo pFirst;
    private Nodo pLast;
    private int size;

    public Lista() {
        this.pFirst = this.pLast = null;
        this.size = 0;
    }

    public void insertar(Nodo integrante) {
        if (this.isEmpty()) {
            this.setpFirst(this.pLast = integrante);
        } else {
            this.pLast.setpNext(integrante);
            this.setpLast(integrante);
        }
        this.setSize(this.getSize() + 1);
    }
    
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

    public boolean isEmpty() {
        return this.getpFirst() == null;
    }

    public Nodo buscar(String clave, int modo) {
        if (this.isEmpty()) {
            return null;
        } else {
            Nodo aux = this.getpFirst();

            if (modo == 1) {
                String claves[] = clave.split(",");

                    while (aux != null) {
                        if(aux.getNombre().trim().toLowerCase().equals(claves[0].trim().toLowerCase()) && claves[1].trim().toLowerCase().contains(aux.getNumeral().trim().toLowerCase())){
                            return aux;
                        }
//                        System.out.println(aux.nombre + "*****" + aux.numeral);
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
     * @return the pFirst
     */
    public Nodo getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the pLast
     */
    public Nodo getpLast() {
        return pLast;
    }

    /**
     * @param pLast the pLast to set
     */
    public void setpLast(Nodo pLast) {
        this.pLast = pLast;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    }
