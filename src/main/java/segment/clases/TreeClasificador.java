/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import segment.modelo.Clasificador;

/**
 *
 * @author jmferreira
 */
public class TreeClasificador {

    private static final Logger LOG = Logger.getLogger(TreeClasificador.class.getName());

    private List<Clasificador> subList2;
    private List<Clasificador> listaPadres;
    private List<Clasificador> listaCompleta;
    private TreeNode root;
    private TreeNode hijos;

    public TreeClasificador() {
    }

    public TreeNode init(Clasificador root) {
        return newNodeWithChildren(root, null);
    }

    /**
     * recursive function that returns a new node with its children
     *
     * @param ttParent
     * @param parent
     * @return
     */
    private TreeNode newNodeWithChildren(Clasificador ttParent, TreeNode parent) {
        TreeNode newNode = new DefaultTreeNode(ttParent, parent);
        for (Clasificador tt : ttParent.getClasificadorList()) {
            TreeNode newNode2 = newNodeWithChildren(tt, newNode);
        }
        return newNode;
    }

    public void expandedAll(final TreeNode node, final boolean expanded) {
        for (final TreeNode child : node.getChildren()) {
            expandedAll(child, expanded);
        }
        node.setExpanded(expanded);
    }

    public String getRuta(Clasificador nodo) {
        if (nodo != null) {
            return calcularRuta("", nodo);
        } else {
            return "/";
        }
    }

    private String calcularRuta(String ruta, Clasificador nodo) {
        if (nodo.getIdPadre() == null) {
            return nodo.getDescripcion();
        } else {
            System.out.println(ruta);
            ruta = calcularRuta(ruta, nodo.getIdPadre()) + "/" + nodo.getDescripcion();
            return ruta;
        }

    }
}
