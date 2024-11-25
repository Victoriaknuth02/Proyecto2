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
 * La clase {@code ArbolGenealogico} representa un árbol genealógico.
 * Permite insertar nodos, buscar nodos, eliminar nodos y realizar diversas
 * operaciones relacionadas con la genealogía, como listar generaciones y
 * obtener títulos únicos.
 */
public class ArbolGenealogico {

    private Nodo raiz;

    /**
     * Crea una instancia de {@code ArbolGenealogico} con la raíz inicializada a {@code null}.
     */
    public ArbolGenealogico() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo nodo en el árbol genealógico.
     *
     * @param nombre     el nombre de la persona.
     * @param numeral    el numeral asociado a la persona.
     * @param ojos       el color de ojos de la persona.
     * @param cabello    el color de cabello de la persona.
     * @param titulo     el título de la persona.
     * @param mote       el mote de la persona.
     * @param esposa     el nombre de la esposa de la persona.
     * @param notas      notas adicionales sobre la persona.
     * @param destino    destino de la persona.
     * @param motePadre  el mote del padre de la persona.
     * @param madre      el nombre de la madre de la persona.
     * @return el nodo recién insertado.
     */
    public Nodo insertar(String nombre, String numeral, String ojos, String cabello, String titulo, String mote, String esposa, String notas, String destino, String motePadre, String madre) {
        Nodo nuevoNodo = new Nodo(nombre, numeral, null, ojos, cabello);
        nuevoNodo.setTitulo(titulo);
        nuevoNodo.setMote(mote);
        nuevoNodo.setEsposa(esposa);
        nuevoNodo.setNotas(notas);
        nuevoNodo.setDestino(destino);
        nuevoNodo.setMadre(madre);

        if (getRaiz() == null) {
            setRaiz(nuevoNodo);
        } else {
            Nodo padre = buscarNodo(getRaiz(), motePadre);
            if (padre != null) {
                nuevoNodo.setPadre(padre);
                agregarHijo(padre, nuevoNodo);
            } else {
                padre = buscarNumeral(motePadre);
                if (padre != null) {
                    nuevoNodo.setPadre(padre);
                    agregarHijo(padre, nuevoNodo);
                }
            }
        }
        return nuevoNodo;
    }

    private void agregarHijo(Nodo padre, Nodo hijo) {
        if (padre.getHijos() == null) {
            padre.setHijos(new Nodo[1]);
            padre.getHijos()[0] = hijo;
        } else {
            Nodo[] nuevoArray = new Nodo[padre.getHijos().length + 1];
            System.arraycopy(padre.getHijos(), 0, nuevoArray, 0, padre.getHijos().length);
            nuevoArray[padre.getHijos().length] = hijo;
            padre.setHijos(nuevoArray);
        }
    }

    /**
     * Elimina un nodo del árbol genealógico por su nombre.
     *
     * @param nombre el nombre del nodo a eliminar.
     */
    public void eliminar(String nombre) {
        setRaiz(eliminarNodo(getRaiz(), nombre));
    }

    private Nodo eliminarNodo(Nodo nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getNombre().equals(nombre)) {
            if (nodo.getHijos() != null && nodo.getHijos().length > 0) {
                return null; // Eliminar el nodo sin hijos
            }
            return null; // Eliminar el nodo
        }
        for (int i = 0; nodo.getHijos() != null && i < nodo.getHijos().length; i++) {
            nodo.getHijos()[i] = eliminarNodo(nodo.getHijos()[i], nombre);
        }
        return nodo;
    }

    /**
     * Busca un nodo en el árbol genealógico por su mote.
     *
     * @param mote el mote del nodo a buscar.
     * @return el nodo encontrado o {@code null} si no se encuentra.
     */
    public Nodo buscar(String mote) {
        return buscarNodo(getRaiz(), mote);
    }

    private Nodo buscarNodo(Nodo nodo, String mote) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getMote().equals(mote)) {
            return nodo;
        }
        if (nodo.getHijos() != null) { // Verifica si hijos no es null
            for ( Nodo hijo : nodo.getHijos()) {
                Nodo resultado = buscarNodo(hijo, mote);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    /**
     * Busca un nodo en el árbol genealógico por su mote y numeral.
     *
     * @param mote el mote del nodo a buscar.
     * @return el nodo encontrado o {@code null} si no se encuentra.
     */
    public Nodo buscarNumeral(String mote) {
        return buscar2(getRaiz(), mote);
    }

    private Nodo buscar2(Nodo nodo, String mote) {
        if (nodo == null) {
            return null;
        }
        String claves[] = mote.split(",");

        if (nodo.getNombre().trim().toLowerCase().equals(claves[0].trim().toLowerCase()) && claves[1].trim().toLowerCase().contains(nodo.getNumeral().trim().toLowerCase())) {
            return nodo;
        }
        if (nodo.getHijos() != null) { // Verifica si hijos no es null
            for (Nodo hijo : nodo.getHijos()) {
                Nodo resultado = buscar2(hijo, mote);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    /**
     * Busca nodos en el árbol genealógico por nombre o mote.
     *
     * @param palabra la palabra a buscar en los nombres y motes.
     * @return una lista de nodos que coinciden con la búsqueda.
     */
    public Lista buscarPorNombreOMote(String palabra) {
        Lista resultados = new Lista();
        buscarPorNombreOMoteNodo(getRaiz(), palabra, resultados);
        return resultados;
    }

    private void buscarPorNombreOMoteNodo(Nodo nodo, String palabra, Lista resultados) {
        if (nodo == null) {
            return;
        }

        // Verificar si el nombre contiene la palabra o si el mote es igual a la palabra
        if (nodo.getNombre() != null && nodo.getNombre().trim().toLowerCase().contains(palabra.trim().toLowerCase()) || (nodo.getMote() != null && nodo.getMote().trim().toLowerCase().equals(palabra.trim().toLowerCase()))) {
            resultados.insertar(nodo, 0);
        }

        // Recorrer los hijos del nodo
        if (nodo.getHijos() != null) {
            for (Nodo hijo : nodo.getHijos()) {
                buscarPorNombreOMoteNodo(hijo, palabra, resultados);
            }
        }
    }

    /**
     * Imprime el árbol genealógico en la consola.
     */
    public void imprimir() {
        imprimirNodo(getRaiz(), 0);
    }

    private void imprimirNodo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.getNombre());
        if (nodo.getHijos() != null) {
            for (Nodo hijo : nodo.getHijos()) {
                imprimirNodo(hijo, nivel + 1);
            }
        }
    }

    /**
     * Muestra los antepasados de un nodo dado.
     *
     * @param nodo el nodo del cual se desean mostrar los antepasados.
     * @return una lista de nodos que representan los antepasados.
     */
    public Lista mostrarAntepasados(Nodo nodo) {
        Lista antepasados = new Lista();
        antepasados.insertar(nodo, 0);

        if (nodo != null) {
            while (nodo.getPadre() != null) {
                antepasados.insertar(nodo.getPadre(), 0);
                nodo = nodo.getPadre();
            }
        }
        return antepasados;
    }

    /**
     * Busca nodos en el árbol genealógico por título.
     *
     * @param titulo el título a buscar.
     * @return una lista de nodos que coinciden con el título.
     */
    public Lista buscarPorTitulo(String titulo) {
        Lista registros = new Lista();
        buscarPorTituloNodo(getRaiz(), titulo, registros);
        return registros;
    }

    private void buscarPorTituloNodo(Nodo nodo, String titulo, Lista registros) {
        if (nodo == null) {
            return;
        }
        if (nodo.getTitulo() != null && nodo.getTitulo().equals(titulo)) {
            registros.insertar(nodo, 0);
        }
        if (nodo.getHijos() != null) { // Ver ifica si hijos no es null
            for (Nodo hijo : nodo.getHijos()) {
                buscarPorTituloNodo(hijo, titulo, registros);
            }
        }
    }

    /**
     * Lista los integrantes de una generación específica.
     *
     * @param generacion la generación a listar.
     * @return una lista de nodos que pertenecen a la generación especificada.
     */
    public Lista listarGeneracion(int generacion) {
        Lista integrantes = new Lista();
        listarGeneracionNodo(getRaiz(), 0, generacion, integrantes);
        return integrantes;
    }

    private void listarGeneracionNodo(Nodo nodo, int generacionActual, int generacionBuscada, Lista integrantes) {
        if (nodo == null) {
            return;
        }
        if (generacionActual == generacionBuscada) {
            integrantes.insertar(nodo, 0);
        } else {
            if (nodo.getHijos() != null) { // Verifica si hijos no es null
                for (Nodo hijo : nodo.getHijos()) {
                    listarGeneracionNodo(hijo, generacionActual + 1, generacionBuscada, integrantes);
                }
            }
        }
    }

    /**
     * Obtiene títulos únicos de los nodos en el árbol genealógico.
     *
     * @return un arreglo de cadenas que contiene títulos únicos.
     */
    public String[] obtenerTitulosUnicos() {
        Lista titulosUnicos = new Lista();
        obtenerTitulosUnicosNodo(getRaiz(), titulosUnicos);

        String[] resultado = new String[titulosUnicos.getSize()];
        Nodo actual = titulosUnicos.getpFirst();
        int index = 0;

        while (actual != null) {
            resultado[index++] = actual.getTitulo();
            actual = actual.getpNext();
        }

        return resultado;
    }

    private void obtenerTitulosUnicosNodo(Nodo nodo, Lista titulosUnicos) {
        if (nodo == null) {
            return;
        }

        if (nodo.getTitulo() != null && !nodo.getTitulo().trim().isEmpty()) {
            if (!estaEnLista(titulosUnicos, nodo.getTitulo())) {
                titulosUnicos.insertar(nodo, 0);
            }
        }

        if (nodo.getHijos() != null) {
            for (Nodo hijo : nodo.getHijos()) {
                obtenerTitulosUnicosNodo(hijo, titulosUnicos);
            }
        }
    }

    /**
     * Método auxiliar para verificar si un título ya está en la lista.
     *
     * @param lista  la lista de títulos únicos.
     * @param titulo el título a verificar.
     * @return {@code true} si el título ya está en la lista, {@code false} en caso contrario.
     */
    private boolean estaEnLista(Lista lista, String titulo) {
        Nodo actual = lista.getpFirst();
        while (actual != null) {
            if (actual.getTitulo().equals(titulo)) {
                return true;
            }
            actual = actual.getpNext();
        }
        return false;
    }

    /**
     * Obtiene la raíz del árbol genealógico.
     *
     * @return el nodo raíz del árbol.
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * Establece la raíz del árbol genealógico.
     *
     * @param raiz el nodo raíz a establecer.
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
}