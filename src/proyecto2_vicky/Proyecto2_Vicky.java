/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2_vicky;

/**
 *
 * @author vicky
 */
public class Proyecto2_Vicky {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolGenealogico arbol = new ArbolGenealogico();

        // Insertar nodos
        arbol.insertar("Juan", "I", "azul", "castaño", "Duque", "El Valiente", "Maria", "Notas de Juan", "Destino de Juan", null);
        arbol.insertar("Pedro", "I", "verde", "rubio", "Conde", "El Sabio", "Ana", "Notas de Pedro", "Destino de Pedro", "Juan");
        arbol.insertar("Luis", "I", "marrón", "negro", "Barón", "El Fuerte", "Clara", "Notas de Luis", "Destino de Luis", "Juan");
        arbol.insertar("Carlos", "II", "azul", "castaño", "Duque", "El Noble", "Sofia", "Notas de Carlos", "Destino de Carlos", "Pedro");

        // Imprimir el árbol después de las inserciones
        System.out.println("Árbol después de inserciones:");
        arbol.imprimir();

        // Buscar un nodo
        Nodo encontrado = arbol.buscar("El Sabio");
        System.out.println("Buscando a Pedro: " + (encontrado != null ? "Encontrado" : "No encontrado"));

        // Mostrar antepasados de Carlos
        arbol.mostrarAntepasados("El Noble");

        // Buscar por título
        arbol.buscarPorTitulo("Duque");

        // Listar integrantes de una generación (nivel 1)
        arbol.listarGeneracion(1);

        // Eliminar un nodo
        arbol.eliminar("El Sabio");
        System.out.println("Árbol después de eliminar a Pedro:");
        arbol.imprimir();

        // Mostrar antepasados de Luis
        arbol.mostrarAntepasados("El Fuerte");

        // Buscar por título de nuevo
        arbol.buscarPorTitulo("Duque");

        // Listar integrantes de una generación (nivel 0)
        arbol.listarGeneracion(0);
    }

    
    
}
