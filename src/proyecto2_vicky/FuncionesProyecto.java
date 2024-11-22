/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author Alesia
 */
public class FuncionesProyecto {
    private ArbolGenealogico arbolGenealogico;
    private TablaHash tablaHash;

    public FuncionesProyecto(ArbolGenealogico arbolGenealogico, TablaHash tablaHash) {
        this.arbolGenealogico = arbolGenealogico;
        this.tablaHash = tablaHash;
    }

    public void mostrarRegistro(String mote) {
        Nodo integrante = arbolGenealogico.buscar(mote);
        if (integrante != null) {
            System.out.println("Nombre: " + integrante.nombre);
            System.out.println("Numeral: " + integrante.numeral);
            System.out.println("Ojos: " + integrante.ojos);
            System.out.println("Cabello: " + integrante.cabello);
            System.out.println("Título: " + integrante.titulo);
            System.out.println("Mote: " + integrante.mote);
            System.out.println("Esposa: " + integrante.esposa);
            System.out.println("Notas: " + integrante.notas);
            System.out.println("Destino: " + integrante.destino);
        } else {
            System.out.println("No se encontró el integrante con el mote: " + mote);
        }
    }

    public void buscarPorNombre(String busqueda) {
        System.out.println("Registros que coinciden con: " + busqueda);
        for (int i = 0; i < tablaHash.size; i++) {
            Nodo encontrado = tablaHash.integrantes[i].buscar(busqueda.toLowerCase(), 1);
            if (encontrado != null) {
                System.out.println("Nombre: " + encontrado.nombre + ", Mote: " + encontrado.mote);
            }
        }
    }

    public void mostrarAntepasados(String mote) {
        arbolGenealogico.mostrarAntepasados(mote);
    }

    public void buscarPorTitulo(String titulo) {
        System.out.println("Registros con el título: " + titulo);
        arbolGenealogico.buscarPorTitulo(titulo);
    }

    public void listarGeneracion(int generacion) {
        arbolGenealogico.listarGeneracion(generacion);
    }
}
