/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.clases;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import segment.modelo.Tramitacion;

/**
 *
 * @author jmferreira
 */
public class TreeTramitacion {

    public TreeNode init(Tramitacion root) {
        return newNodeWithChildren(root, null);
    }

    /**
     * recursive function that returns a new node with its children
     *
     * @param ttParent
     * @param parent
     * @return
     */
    public TreeNode newNodeWithChildren(Tramitacion ttParent, TreeNode parent) {
        TreeNode newNode = new DefaultTreeNode(ttParent, parent);
        for (Tramitacion tt : ttParent.getTramitacionList()) {
            TreeNode newNode2 = newNodeWithChildren(tt, newNode);
        }
        return newNode;
    }

}
