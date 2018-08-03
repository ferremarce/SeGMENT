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
import segment.modelo.Dependencia;

/**
 *
 * @author jmferreira
 */
@Stateless
public class DependenciaFacade extends AbstractFacade<Dependencia> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DependenciaFacade() {
        super(Dependencia.class);
    }

    public List<Dependencia> findDependencia(String criterio) {
        Query q = em.createQuery("SELECT a FROM Dependencia a WHERE UPPER(a.descripcionDependencia) LIKE :xCriterio ORDER BY a.orden");
        q.setParameter("xCriterio", "%" + criterio.toUpperCase() + "%");
        List<Dependencia> tr = q.getResultList();
        return tr;

    }

    public List<Dependencia> findAllDependencia() {
        Query q = em.createQuery("SELECT a FROM Dependencia a WHERE a.activo=1");
        List<Dependencia> tr = q.getResultList();
        return tr;

    }

}
