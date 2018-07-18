/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import segment.modelo.PermisoRol;

/**
 *
 * @author jmferreira
 */
@Stateless
public class PermisoRolFacade extends AbstractFacade<PermisoRol> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisoRolFacade() {
        super(PermisoRol.class);
    }
    
}
