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
 * La clase {@code TablaHash} implementa una tabla hash para almacenar nodos que
 * representan integrantes de un árbol genealógico. Utiliza listas enlazadas
 * para manejar colisiones y permite insertar y buscar integrantes de manera
 * eficiente.
 */
public class TablaHash {

    private Lista[] integrantes; // Arreglo de listas enlazadas para almacenar los integrantes
    private int size;            // Tamaño de la tabla hash
    private int count;           // Contador de elementos en la tabla

    /**
     * Crea una nueva instancia de {@code TablaHash} con el tamaño especificado.
     *
     * @param size el tamaño de la tabla hash.
     */
    public TablaHash(int size) {
        this.size = size;
        this.count = 0;
        this.integrantes = new Lista[this.size];
        for (int i = 0; i < this.size; i++) {
            this.integrantes[i] = new Lista();
        }
    }

    /**
     * Calcula el valor hash para una clave dada.
     *
     * @param mote_integrante la clave para la cual se calculará el hash.
     * @return el valor hash correspondiente a la clave.
     */
    public int hash(String mote_integrante) {
        int hash = 1;
        if (mote_integrante.length() > 0) {
            for (int i = 0; i < mote_integrante.length(); i++) {
                hash += hash * 31 + mote_integrante.charAt(i);
            }
        }
        return hash % this.getSize();
    }

    /**
     * Inserta un nodo en la tabla hash según el modo especificado.
     *
     * @param integrante el nodo a insertar.
     * @param modo el modo de inserción (1: nombre y numeral, 2: mote, 3:
     * título).
     */
    public void insertar(Nodo integrante, int modo) {
        int hash = -1;
        if (modo == 1) {
            hash = this.hash(integrante.getNombre().toLowerCase() + ", " + integrante.getNumeral().toLowerCase() + " of his name");
        } else if (modo == 2) {
            hash = this.hash(integrante.getMote().toLowerCase());
        } else if (modo == 3) {
            hash = this.hash(integrante.getTitulo().toLowerCase());
        }

        if (hash < 0) {
            hash *= -1;
        }

        if (this.getIntegrantes()[hash].buscar(integrante.getNombre().toLowerCase() + ", " + integrante.getNumeral().toLowerCase() + " of his name", 1) != null
                && this.getIntegrantes()[hash].buscar(integrante.getMote().toLowerCase(), 2) != null
                && this.getIntegrantes()[hash].buscar(integrante.getTitulo().toLowerCase(), 3) != null) {
            return; // El nodo ya existe, no se inserta
        } else {
            this.getIntegrantes()[hash].insertar(integrante);
            this.setCount(this.getCount() + 1);
        }
    }

    /**
     * Busca un nodo en la tabla hash según una clave y un modo de búsqueda.
     *
     * @param clave la clave a buscar.
     * @param modo el modo de búsqueda (1: nombre y numeral, 2: mote, 3:
     * título).
     * @return el nodo encontrado o {@code null} si no se encuentra.
     */
    public Nodo buscar(String clave, int modo) {
        int hash = this.hash(clave.toLowerCase());
        if (hash < 0) {
            hash *= -1;
        }

        if (modo == 1) {
            String claves[] = clave.split(",");
            if (this.getIntegrantes()[hash].getpFirst().getNombre().trim().toLowerCase().equals(claves[0].trim().toLowerCase())
                    && claves[1].trim().toLowerCase().contains(this.getIntegrantes()[hash].getpFirst().getNumeral().trim().toLowerCase())) {
                return this.getIntegrantes()[hash].getpFirst();
            } else {
                return this.getIntegrantes()[hash].buscar(clave, 1);
            }
        } else if (modo == 2) {
            if (this.getIntegrantes()[hash].getpFirst().getMote().toLowerCase().equals(clave.toLowerCase())) {
                return this.getIntegrantes()[hash].getpFirst();
            } else {
                return this.getIntegrantes()[hash].buscar(clave, 2);
            }
        } else if (modo == 3) {
            if (this.getIntegrantes()[hash].getpFirst().getTitulo().toLowerCase().equals(clave.toLowerCase())) {
                return this.getIntegrantes()[hash].getpFirst();
            } else {
                return this.getIntegrantes()[hash].buscar(clave, 3);
            }
        }
        return null; // Si no se encuentra el nodo
    }

    /**
     * @return the integrantes
     */
    public Lista[] getIntegrantes() {
        return integrantes;
    }

    /**
     * @param integrantes the integrantes to set
     */
    public void setIntegrantes(Lista[] integrantes) {
        this.integrantes = integrantes;
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

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

}
