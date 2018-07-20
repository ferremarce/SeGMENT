/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.clases;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import segment.modelo.Tree;

/**
 *
 * @author jmferreira
 */
public class MyTree {

    private List<Tree> subList2;
    private List<Tree> listaPadres;
    private List<Tree> listaCompleta;
    private TreeNode root;
    private TreeNode hijos;

    public MyTree() {
    }

    public TreeNode crearArbol(List<Tree> listaClasif) {
        this.listaCompleta = listaClasif;
        root = new DefaultTreeNode("Raiz", null);
        //hijos = new DefaultTreeNode("Inicio", root);
        recursive(listaClasif, 0, root);
        //hijos.setExpanded(true);
        return root;
    }

    private void recursive(List<Tree> listaClasif, Integer id, TreeNode node) {
        subList2 = new ArrayList<>();
        subList2 = getRegistroByPadre(id);

        for (Tree k : subList2) {
            TreeNode childNode = new DefaultTreeNode(k, node);

            recursive(subList2, k.getIdTree(), childNode);
        }
    }

    public List<Tree> getRegistroByPadre(Integer i) {
        listaPadres = new ArrayList<>();
        try {
            for (Tree k : this.listaCompleta) {
                if (i == 0 && k.getIdPadre() == null) {
                    listaPadres.add(k);
                } else {
                    if (k.getIdPadre() != null && k.getIdPadre().getIdTree().compareTo(i) == 0) {
                        listaPadres.add(k);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaPadres;
    }

    public String getRuta(Tree nodo) {
        if (nodo != null) {
            return calcularRuta("/" + nodo.getDescripcion(), nodo);
        } else {
            return "/";
        }
    }

    private String calcularRuta(String ruta, Tree nodo) {
        if (nodo.getIdPadre() != null) {
            ruta = ruta + "/" + nodo.getIdPadre().getDescripcion();
            return calcularRuta(ruta, nodo.getIdPadre());
        } else {
            return ruta;
        }

    }
}
