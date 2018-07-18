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
import segment.modelo.Expediente;

/**
 *
 * @author jmferreira
 */
@Stateless
public class ExpedienteFacade extends AbstractFacade<Expediente> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExpedienteFacade() {
        super(Expediente.class);
    }

    public List<Expediente> findExpediente(String criterio) {
        Query q = em.createQuery("SELECT a FROM Expediente a WHERE UPPER(a.acapite) LIKE :xCriterio ORDER BY a.fechaExpediente");
        q.setParameter("xCriterio", "%" + criterio.toUpperCase() + "%");
        List<Expediente> tr = q.getResultList();
        return tr;

    }

}
