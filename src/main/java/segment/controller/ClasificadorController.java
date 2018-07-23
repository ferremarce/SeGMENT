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
import segment.fachada.ClasificadorFacade;
import segment.modelo.Clasificador;

/**
 *
 * @author jmferreira
 */
@Named(value = "ClasificadorController")
@SessionScoped
public class ClasificadorController implements Serializable {

    @Inject
    ClasificadorFacade clasificadorFacade;
    private Clasificador clasificador;
    private TreeNode root1;
    private TreeNode selectedNode1;
    private Clasificador nodoSeleccionado;
    private List<Clasificador> listaHijos;

    /**
     * Creates a new instance of TreeController
     */
    public ClasificadorController() {
    }

    public List<Clasificador> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(List<Clasificador> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public ClasificadorFacade getClasificadorFacade() {
        return clasificadorFacade;
    }

    public void setClasificadorFacade(ClasificadorFacade clasificadorFacade) {
        this.clasificadorFacade = clasificadorFacade;
    }

    

    public Clasificador getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    public void setNodoSeleccionado(Clasificador nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    public void init() {
        MyTree t = new MyTree();
        root1 = t.crearArbol(clasificadorFacade.findAll());
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

    public Clasificador getClasificador() {
        return clasificador;
    }

    public void setClasificador(Clasificador clasificador) {
        this.clasificador = clasificador;
    }

    

    public String doClasificadorForm() {
        this.init();
        return "/pages/TreeCRUD";
    }

    public void onNodeSelectTree(NodeSelectEvent event) {
        this.nodoSeleccionado = (Clasificador) selectedNode1.getData();
        if (!this.nodoSeleccionado.getClasificadorList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getClasificadorList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        //System.out.println("click en " + this.nodoSeleccionado.getIdTree() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public void doDownNode(Clasificador nodoClick) {
        this.nodoSeleccionado = nodoClick;
        if (!this.nodoSeleccionado.getClasificadorList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getClasificadorList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        System.out.println("click en " + this.nodoSeleccionado.getIdClasificador() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public void doUpNode() {
        this.nodoSeleccionado = this.nodoSeleccionado.getIdPadre();
        if (!this.nodoSeleccionado.getClasificadorList().isEmpty()) {
            this.listaHijos = this.nodoSeleccionado.getClasificadorList();
        } else {
            this.listaHijos = new ArrayList<>();
        }
        System.out.println("click en " + this.nodoSeleccionado.getIdClasificador() + "-" + this.nodoSeleccionado.getDescripcion());
    }

    public String doRuta() {
        MyTree t = new MyTree();
        return t.getRuta(nodoSeleccionado);
    }

}
