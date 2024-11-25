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

    private Lista[] integrantes;
    private int size;
    private int count;

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
        return hash % this.getSize();
    }

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
        if (hash < 0) {
            hash *= -1;
        }

        if (this.getIntegrantes()[hash].buscar(integrante.getNombre().toLowerCase() + ", " + integrante.getNumeral().toLowerCase() + " of his name", 1) != null && this.getIntegrantes()[hash].buscar(integrante.getMote().toLowerCase(), 2) != null && this.getIntegrantes()[hash].buscar(integrante.getTitulo().toLowerCase(), 3) != null) {
            return;
        } else {
            this.getIntegrantes()[hash].insertar(integrante);
            this.setCount(this.getCount() + 1);
        }
    }

    public Nodo buscar(String clave, int modo) {
        int hash = this.hash(clave.toLowerCase());
        if (hash < 0) {
            hash *= -1;
        }
        if (modo == 1) {
            String claves[] = clave.split(",");
//            System.out.println("ppp   "+this.integrantes[hash].pFirst.nombre + ", " + this.integrantes[hash].pFirst.numeral + " of his name");
//                                        System.out.println("----------" +clave);

            if (this.getIntegrantes()[hash].getpFirst().getNombre().trim().toLowerCase().equals(claves[0].trim().toLowerCase()) && claves[1].trim().toLowerCase().contains(this.getIntegrantes()[hash].getpFirst().getNumeral().trim().toLowerCase())) {

                return this.getIntegrantes()[hash].getpFirst();
                
            } else {
//                            System.out.println("----------" +clave);

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
        return null;

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
