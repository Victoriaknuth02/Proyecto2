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

    // Método para eliminar un nodo
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

    // Método para buscar un nodo
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
            for (Nodo hijo : nodo.getHijos()) {
                Nodo resultado = buscarNodo(hijo, mote);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null;
    }

    // Método para buscar un nodo
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
//            System.out.println(nodo.nombre);
            resultados.insertar(nodo, 0);
        }

        // Recorrer los hijos del nodo
        if (nodo.getHijos() != null) {
            for (Nodo hijo : nodo.getHijos()) {
                buscarPorNombreOMoteNodo(hijo, palabra, resultados);
            }
        }
    }

    // Este lo elimino despues, solo es para checkear que todo esta funcioando biennn
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

// Método para mostrar antepasados
    public Lista mostrarAntepasados(Nodo nodo) {
        Lista antepasados = new Lista();
        antepasados.insertar(nodo, 0);

//        Nodo nodo = buscar(mote);
        if (nodo != null) {
            while (nodo.getPadre() != null) {
                antepasados.insertar(nodo.getPadre(), 0);
                nodo = nodo.getPadre();
            }
        }
        return antepasados;
    }

// Método para buscar por título
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
            registros.insertar(nodo, 0); // Asumiendo que el nodo tiene un método para acceder a su nombre
        }
        if (nodo.getHijos() != null) { // Verifica si hijos no es null
            for (Nodo hijo : nodo.getHijos()) {
                buscarPorTituloNodo(hijo, titulo, registros);
            }
        }
    }

// Método para listar integrantes de una generación
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
            integrantes.insertar(nodo, 0); // Asumiendo que el nodo tiene un método para acceder a su nombre
        } else {
            if (nodo.getHijos() != null) { // Verifica si hijos no es null
                for (Nodo hijo : nodo.getHijos()) {
                    listarGeneracionNodo(hijo, generacionActual + 1, generacionBuscada, integrantes);
                }
            }
        }
    }

    public String[] obtenerTitulosUnicos() {
        Lista titulosUnicos = new Lista(); // Usar tu clase Lista para almacenar títulos únicos
        obtenerTitulosUnicosNodo(getRaiz(), titulosUnicos);

        // Convertir la lista de títulos únicos a un arreglo de Strings
        String[] resultado = new String[titulosUnicos.getSize()]; // Usar size directamente de la clase Lista
        Nodo actual = titulosUnicos.getpFirst(); // Comenzar desde el primer nodo de la lista
        int index = 0;

        // Llenar el arreglo con los títulos únicos
        while (actual != null) {
//            System.out.println("skajhksjha");
            resultado[index++] = actual.getTitulo(); // Asumiendo que el nodo tiene un campo titulo
            actual = actual.getpNext(); // Pasar al siguiente nodo
        }

        return resultado;
    }

    private void obtenerTitulosUnicosNodo(Nodo nodo, Lista titulosUnicos) {
        if (nodo == null) {
            return;
        }

        // Agregar el título al conjunto si no es nulo y no está vacío
        if (nodo.getTitulo() != null && !nodo.getTitulo().trim().isEmpty()) {
            // Verificar si el título ya está en la lista
            if (!estaEnLista(titulosUnicos, nodo.getTitulo())) {
                titulosUnicos.insertar(nodo, 0); // Insertar el nodo directamente en la lista
            }
        }

        // Recorrer los hijos del nodo
        if (nodo.getHijos() != null) {
            for (Nodo hijo : nodo.getHijos()) {
                obtenerTitulosUnicosNodo(hijo, titulosUnicos);
            }
        }
    }

// Método auxiliar para verificar si un título ya está en la lista
    private boolean estaEnLista(Lista lista, String titulo) {
        Nodo actual = lista.getpFirst(); // Comenzar desde el primer nodo de la lista
        while (actual != null) {
            if (actual.getTitulo().equals(titulo)) { // Comparar títulos
                return true; // El título ya está en la lista
            }
            actual = actual.getpNext(); // Pasar al siguiente nodo
        }
        return false; // El título no está en la lista
    }

    /**
     * @return the raiz
     */
    public Nodo getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
}
