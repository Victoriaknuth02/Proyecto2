/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto2_vicky;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

/**
 * La clase {@code GraphStream} es una interfaz gráfica que visualiza un árbol genealógico
 * utilizando la biblioteca GraphStream. Permite interactuar con los nodos del árbol
 * y muestra detalles sobre cada nodo al hacer clic en ellos.
 */
public class GraphStream extends javax.swing.JFrame implements ViewerListener {

    static ArbolGenealogico tree;
    Graph graph;
    final ViewerPipe fromviewer;

    /**
     * Crea una nueva instancia de {@code GraphStream} y configura la visualización
     * del árbol genealógico.
     *
     * @param tree el árbol genealógico que se va a visualizar.
     */
    public GraphStream(ArbolGenealogico tree) {
        this.tree = tree;
        initComponents();
        this.setLocationRelativeTo(null);
        graph = new SingleGraph("a");

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        View view = viewer.addDefaultView(false);
        viewer.getDefaultView().enableMouseOptions();
        GraphStreamPanel.setLayout(new BorderLayout());
        GraphStreamPanel.add((Component) view, BorderLayout.CENTER);
        GraphStreamPanel.setPreferredSize(new Dimension(2060, 1200));
        GraphStreamPanel.setFocusable(true);
        GraphStreamPanel.requestFocusInWindow();
        GraphStreamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fromviewer = viewer.newViewerPipe();
        fromviewer.addViewerListener(this);
        fromviewer.addSink(graph);

        populateGraph(tree.getRaiz(), 0, 0);

        PumpViewer();

        GraphStreamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX();
                int clickY = e.getY();
                System.out.println("Presionado" + clickX + "  " + clickY);
                for (Node node : graph) {
                    double[] position = (double[]) node.getAttribute("xy");
                    double nodeX = position[0];
                    double nodeY = position[1];
                    System.out.println("Nodo: " + nodeX + "     " + nodeY);
                    if (Math.abs(clickX - nodeX) < 50 && Math.abs(clickY - nodeY) < 50) {
                        buttonPushed(node.getId());
                        break;
                    }
                }
            }
        });
    }

    /**
     * Crea una nueva instancia de {@code GraphStream} y configura la visualización
     * del árbol genealógico, incluyendo una lista de antepasados.
     *
     * @param tree el árbol genealógico que se va a visualizar.
     * @param ant  la lista de antepasados a incluir en la visualización.
     */
    public GraphStream(ArbolGenealogico tree, Lista ant) {
        this.tree = tree;
        initComponents();
        this.setLocationRelativeTo(null);
        graph = new SingleGraph("a");

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        View view = viewer.addDefaultView(false);
        viewer.getDefaultView().enableMouseOptions();
        GraphStreamPanel.setLayout(new BorderLayout());
        GraphStreamPanel.add((Component) view, BorderLayout.CENTER);
        GraphStreamPanel.setPreferredSize(new Dimension(2060, 1200));
        GraphStreamPanel.setFocusable(true);
        GraphStreamPanel.requestFocusInWindow();
        GraphStreamPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fromviewer = viewer.newViewerPipe();
        fromviewer.addViewerListener(this);
        fromviewer.addSink(graph);

        populateGraph(tree.getRaiz(), 0, 0, ant);

        PumpViewer();

        GraphStreamPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickX = e.getX();
                int clickY = e.getY();
                System.out.println("Presionado" + clickX + "  " + clickY);
                for (Node node : graph) {
                    double[] position = (double[]) node.getAttribute("xy");
                    double nodeX = position [0];
                    double nodeY = position[1];
                    System.out.println("Nodo: " + nodeX + "     " + nodeY);
                    if (Math.abs(clickX - nodeX) < 50 && Math.abs(clickY - nodeY) < 50) {
                        buttonPushed(node.getId());
                        break;
                    }
                }
            }
        });
    }

    /**
     * Población del gráfico a partir de un nodo dado, estableciendo su posición
     * en el gráfico.
     *
     * @param nodo el nodo a agregar al gráfico.
     * @param x    la coordenada x para la posición del nodo.
     * @param y    la coordenada y para la posición del nodo.
     */
    private void populateGraph(Nodo nodo, int x, int y) {
        if (nodo == null) {
            return;
        }
        String clave = nodo.getNombre() + "/" + nodo.getNumeral() + "/" + nodo.getMote();
        graph.addNode(clave);
        Node node = graph.getNode(clave);
        node.setAttribute("xyz", x, y, 1);
        node.setAttribute("ui.label", nodo.getNombre() + ", " + nodo.getNumeral() + " of his name");

        node.setAttribute("ui.style", "shape: box; fill-color: lightblue; size: 40px; padding: 0px; stroke-width: 2px;");
        node.setAttribute("ui.draggable", false);
        node.setAttribute("ui.clickable", true);

        if (nodo.getPadre() != null && nodo != tree.getRaiz()) {
            String clave2 = nodo.getPadre().getNombre() + "/" + nodo.getPadre().getNumeral() + "/" + nodo.getPadre().getMote();
            graph.addEdge(clave2 + "-" + clave, clave2, clave);
        }

        if (nodo.getHijos() != null) {
            int childX = x - (nodo.getHijos().length * 50) / 2;
            int childY = y - 100;
            for (Nodo hijo : nodo.getHijos()) {
                populateGraph(hijo, childX, childY);
                childX += 200;
            }
        }
    }

    /**
     * Población del gráfico a partir de un nodo dado, incluyendo una lista de
     * antepasados.
     *
     * @param nodo el nodo a agregar al gráfico.
     * @param x    la coordenada x para la posición del nodo.
     * @param y    la coordenada y para la posición del nodo.
     * @param ant  la lista de antepasados a incluir en la visualización.
     */
    private void populateGraph(Nodo nodo, int x, int y, Lista ant) {
        if (nodo == null) {
            return;
        }

        String clave = nodo.getNombre() + "/" + nodo.getNumeral();
        if (ant.buscar(nodo.getNombre() + ", " + nodo.getNumeral() + " of his name", 1) != null) {
            graph.addNode(clave);
            Node node = graph.getNode(clave);
            node.setAttribute("xyz", x, y, 1);
            node.setAttribute("ui.label", nodo.getNombre() + ", " + nodo.getNumeral() + " of his name");
            node.setAttribute("ui.style", "shape: box; fill-color: lightblue; size: 40px; padding: 0px; stroke-width: 2px;");
            node.setAttribute("ui.draggable", false);
            node.setAttribute("ui.clickable", true);

            if (nodo.getPadre() != null && nodo != tree.getRaiz()) {
                String clave2 = nodo.getPadre().getNombre() + "/" + nodo.getPadre().getNumeral();
                graph.addEdge(clave2 + "-" + clave, clave2, clave);
            }
        }
        if (nodo.getHijos() != null) {
            int childX = x - (nodo.getHijos().length * 50) / 2;
            int childY = y - 100;
            for (Nodo hijo : nodo.getHijos()) {
                populateGraph(hijo, childX, childY, ant);
                childX += 200;
            }
        }
    }

    /**
     * Inicia un hilo para actualizar la visualización del gráfico.
     */
    private void PumpViewer() {
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!isCancelled()) {
                    fromviewer.pump();
 try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                return null;
            }
        };
        worker.execute();
    }

    @Override
    public void viewClosed(String id) {
    }

    @Override
    public void buttonPushed(String id) {
        Node node = graph.getNode(id);
        if (node != null) {
            String nombre = (String) node.getAttribute("ui.label");
            String mensaje = tree.buscarNumeral(nombre).toString();
            JOptionPane.showMessageDialog(this, "Detalles: " + mensaje);
        } else {
            System.out.println("El nodo no se encontró: " + id);
        }
    }

    @Override
    public void buttonReleased(String string) {
    }

    @Override
    public void mouseOver(String string) {
    }

    @Override
    public void mouseLeft(String string) {
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GraphStreamPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GraphStreamPanel.setBackground(new java.awt.Color(255, 204, 51));
        GraphStreamPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(GraphStreamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphStream.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphStream(tree).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GraphStreamPanel;
    // End of variables declaration//GEN-END:variables
}
