/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class ArbolGenealogico {

    private Nodo raiz;

    public ArbolGenealogico() {
        this.raiz = null;
    }

    // Cambiar luego para que inserte Nodos directamente (creo que solo lo necesitaremos para leer el archivo)
    public void insertar(String nombre, String numeral, String ojos, String cabello, String titulo, String mote, String esposa, String notas, String destino, String motePadre) {
        Nodo nuevoNodo = new Nodo(nombre, numeral, null, ojos, cabello);
        nuevoNodo.titulo = titulo;
        nuevoNodo.mote = mote;
        nuevoNodo.esposa = esposa;
        nuevoNodo.notas = notas;
        nuevoNodo.destino = destino;

        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            Nodo padre = buscarNodo(raiz, motePadre);
            if (padre != null) {
                nuevoNodo.padre = padre;
                agregarHijo(padre, nuevoNodo);
            }
        }
    }

    private void agregarHijo(Nodo padre, Nodo hijo) {
        if (padre.hijos == null) {
            padre.hijos = new Nodo[1];
            padre.hijos[0] = hijo;
        } else {
            Nodo[] nuevoArray = new Nodo[padre.hijos.length + 1];
            System.arraycopy(padre.hijos, 0, nuevoArray, 0, padre.hijos.length);
            nuevoArray[padre.hijos.length] = hijo;
            padre.hijos = nuevoArray;
        }
    }

    // Método para eliminar un nodo
    public void eliminar(String nombre) {
        raiz = eliminarNodo(raiz, nombre);
    }

    private Nodo eliminarNodo(Nodo nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nodo.nombre.equals(nombre)) {
            if (nodo.hijos != null && nodo.hijos.length > 0) {
                return null; // Eliminar el nodo sin hijos
            }
            return null; // Eliminar el nodo
        }
        for (int i = 0; nodo.hijos != null && i < nodo.hijos.length; i++) {
            nodo.hijos[i] = eliminarNodo(nodo.hijos[i], nombre);
        }
        return nodo;
    }

    // Método para buscar un nodo
    public Nodo buscar(String mote) {
        return buscarNodo(raiz, mote);
    }

    private Nodo buscarNodo(Nodo nodo, String mote) {
        if (nodo == null) {
            return null;
        }
        if (nodo.mote.equals(mote)) {
            return nodo;
        }
        if (nodo.hijos != null) { // Verifica si hijos no es null
            for (Nodo hijo : nodo.hijos) {
                Nodo resultado = buscarNodo(hijo, mote);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    // Este lo elimino despues, solo es para checkear que todo esta funcioando biennn
    public void imprimir() {
        imprimirNodo(raiz, 0);
    }

    private void imprimirNodo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.nombre);
        if (nodo.hijos != null) {
            for (Nodo hijo : nodo.hijos) {
                imprimirNodo(hijo, nivel + 1);
            }
        }
    }

    // Método para mostrar antepasados
    public void mostrarAntepasados(String mote) {
        Nodo nodo = buscar(mote);
        if (nodo != null) {
            System.out.println("Antepasados de " + mote + ":");
            while (nodo.padre != null) {
                System.out.println(nodo.padre.mote);
                nodo = nodo.padre;
            }
        } else {
            System.out.println("No se encontró el mote.");
        }
    }

    // Método para buscar por título
    public void buscarPorTitulo(String titulo) {
        System.out.println("Registros con el título " + titulo + ":");
        buscarPorTituloNodo(raiz, titulo);
    }

    private void buscarPorTituloNodo(Nodo nodo, String titulo) {
        if (nodo == null) {
            return;
        }
        if (nodo.titulo != null && nodo.titulo.equals(titulo)) {
            System.out.println(nodo.nombre);
        }
        if (nodo.hijos != null) { // Verifica si hijos no es null
            for (Nodo hijo : nodo.hijos) {
                buscarPorTituloNodo(hijo, titulo);
            }
        }
    }

    // Método para listar integrantes de una generación
    public void listarGeneracion(int generacion) {
        System.out.println("Integrantes de la generación " + generacion + ":");
        listarGeneracionNodo(raiz, 0, generacion);
    }

    private void listarGeneracionNodo(Nodo nodo, int generacionActual, int generacionBuscada) {
        if (nodo == null) {
            return;
        }
        if (generacionActual == generacionBuscada) {
            System.out.println(nodo.nombre);
        } else {
            if (nodo.hijos != null) { // Verifica si hijos no es null
                for (Nodo hijo : nodo.hijos) {
                    listarGeneracionNodo(hijo, generacionActual + 1, generacionBuscada);
                }
            }
        }
    }

}
