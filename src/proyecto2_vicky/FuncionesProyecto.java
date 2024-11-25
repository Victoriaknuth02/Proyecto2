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

    /**
     * @return the arbolGenealogico
     */
    public ArbolGenealogico getArbolGenealogico() {
        return arbolGenealogico;
    }

    /**
     * @param arbolGenealogico the arbolGenealogico to set
     */
    public void setArbolGenealogico(ArbolGenealogico arbolGenealogico) {
        this.arbolGenealogico = arbolGenealogico;
    }

    /**
     * @return the tablaHash
     */
    public TablaHash getTablaHash() {
        return tablaHash;
    }

    /**
     * @param tablaHash the tablaHash to set
     */
    public void setTablaHash(TablaHash tablaHash) {
        this.tablaHash = tablaHash;
    }
    private ArbolGenealogico arbolGenealogico;
    private TablaHash tablaHash;

    public FuncionesProyecto(ArbolGenealogico arbolGenealogico, TablaHash tablaHash) {
        this.arbolGenealogico = arbolGenealogico;
        this.tablaHash = tablaHash;
    }

    public void mostrarRegistro(String mote) {
        Nodo integrante = getArbolGenealogico().buscar(mote);
        if (integrante != null) {
            System.out.println("Nombre: " + integrante.getNombre());
            System.out.println("Numeral: " + integrante.getNumeral());
            System.out.println("Ojos: " + integrante.getOjos());
            System.out.println("Cabello: " + integrante.getCabello());
            System.out.println("Título: " + integrante.getTitulo());
            System.out.println("Mote: " + integrante.getMote());
            System.out.println("Esposa: " + integrante.getEsposa());
            System.out.println("Notas: " + integrante.getNotas());
            System.out.println("Destino: " + integrante.getDestino());
        } else {
            System.out.println("No se encontró el integrante con el mote: " + mote);
        }
    }

    public void buscarPorNombre(String busqueda) {
        System.out.println("Registros que coinciden con: " + busqueda);
        for (int i = 0; i < getTablaHash().getSize(); i++) {
            Nodo encontrado = getTablaHash().getIntegrantes()[i].buscar(busqueda.toLowerCase(), 1);
            if (encontrado != null) {
                System.out.println("Nombre: " + encontrado.getNombre() + ", Mote: " + encontrado.getMote());
            }
        }
    }

//    public void mostrarAntepasados(String mote) {
//        arbolGenealogico.mostrarAntepasados(mote);
//    }

    public void buscarPorTitulo(String titulo) {
        System.out.println("Registros con el título: " + titulo);
        getArbolGenealogico().buscarPorTitulo(titulo);
    }

    public void listarGeneracion(int generacion) {
        getArbolGenealogico().listarGeneracion(generacion);
    }
}
