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
public class MyTree {
private static final Logger LOG = Logger.getLogger(MyTree.class.getName());
    private List<Clasificador> subList2;
    private List<Clasificador> listaPadres;
    private List<Clasificador> listaCompleta;
    private TreeNode root;
    private TreeNode hijos;

    public MyTree() {
    }

    public TreeNode crearArbol(List<Clasificador> listaClasif) {
        this.listaCompleta = listaClasif;
        root = new DefaultTreeNode("Raiz", null);
        //hijos = new DefaultTreeNode("Inicio", root);
        recursive(listaClasif, 0, root);
        //hijos.setExpanded(true);
        return root;
    }

    private void recursive(List<Clasificador> listaClasif, Integer id, TreeNode node) {
        subList2 = new ArrayList<>();
        subList2 = getRegistroByPadre(id);

        for (Clasificador k : subList2) {
            TreeNode childNode = new DefaultTreeNode(k, node);

            recursive(subList2, k.getIdClasificador(), childNode);
        }
    }

    public List<Clasificador> getRegistroByPadre(Integer i) {
        listaPadres = new ArrayList<>();
        try {
            for (Clasificador k : this.listaCompleta) {
                if (i == 0 && k.getIdPadre() == null) {
                    listaPadres.add(k);
                } else {
                    if (k.getIdPadre() != null && k.getIdPadre().getIdClasificador().compareTo(i) == 0) {
                        listaPadres.add(k);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return listaPadres;
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
            ruta = calcularRuta(ruta, nodo.getIdPadre())+"/"+nodo.getDescripcion();
            return ruta;
        }

    }
}
