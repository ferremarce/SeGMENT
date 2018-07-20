/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;
import segment.clases.MyTree;
import segment.fachada.TreeFacade;
import segment.modelo.Tree;

/**
 *
 * @author jmferreira
 */
@Named(value = "TreeController")
@SessionScoped
public class TreeController implements Serializable {

    @Inject
    TreeFacade treeFacade;
    private Tree tree;
    private TreeNode root1;
    private TreeNode selectedNode1;
    private Tree nodoSeleccionado;
    private List<Tree> listaHijos;

    /**
     * Creates a new instance of TreeController
     */
    public TreeController() {
    }

    public List<Tree> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(List<Tree> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public TreeFacade getTreeFacade() {
        return treeFacade;
    }

    public void setTreeFacade(TreeFacade treeFacade) {
        this.treeFacade = treeFacade;
    }

    public Tree getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(Tree nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public void init() {
        MyTree t = new MyTree();
        root1 = t.crearArbol(treeFacade.findAll());
        root1.setExpanded(true);
        for (TreeNode n : root1.getChildren()) {
            n.setExpanded(true);
            for (TreeNode n1 : n.getChildren()) {
                n1.setExpanded(true);
            }
        }
    }

    public TreeNode getRoot1() {
        return root1;
    }

    public void setRoot1(TreeNode root1) {
        this.root1 = root1;
    }

    public TreeNode getSelectedNode1() {
        return selectedNode1;
    }

    public void setSelectedNode1(TreeNode selectedNode1) {
        this.selectedNode1 = selectedNode1;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public String doTreeForm() {
        this.init();
        return "/pages/TreeCRUD";
    }

    public void onNodeSelectTree(NodeSelectEvent event) {
        this.nodoSeleccionado = (Tree) selectedNode1.getData();
        if (!this.nodoSeleccionado.getTreeList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getTreeList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        //System.out.println("click en " + this.nodoSeleccionado.getIdTree() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public void doDownNode(Tree nodoClick) {
        this.nodoSeleccionado = nodoClick;
        if (!this.nodoSeleccionado.getTreeList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getTreeList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        System.out.println("click en " + this.nodoSeleccionado.getIdTree() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public void doUpNode() {
        this.nodoSeleccionado = this.nodoSeleccionado.getIdPadre();
        if (!this.nodoSeleccionado.getTreeList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getTreeList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        System.out.println("click en " + this.nodoSeleccionado.getIdTree() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public String doRuta() {
        MyTree t = new MyTree();
        return t.getRuta(nodoSeleccionado);
    }

}
