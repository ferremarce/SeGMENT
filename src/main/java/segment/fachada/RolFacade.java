/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.fachada;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import segment.modelo.Rol;

/**
 *
 * @author jmferreira
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }

    public List<Rol> findRol(String criterio) {
        Query q = em.createQuery("SELECT a FROM Rol a WHERE UPPER(a.descripcionRol) LIKE :xCriterio");
        q.setParameter("xCriterio", "%" + criterio.toUpperCase() + "%");
        List<Rol> tr = q.getResultList();
        return tr;

    }
}
