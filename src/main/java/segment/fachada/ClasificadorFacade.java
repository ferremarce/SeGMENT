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
import segment.modelo.Clasificador;

/**
 *
 * @author jmferreira
 */
@Stateless
public class ClasificadorFacade extends AbstractFacade<Clasificador> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificadorFacade() {
        super(Clasificador.class);
    }

    public Clasificador findFirstClasificador() {
        //Todos los estados menos el Pendiente
        Query q = em.createQuery("SELECT a FROM Clasificador a WHERE a.idPadre IS NULL");
        List<Clasificador> tr = q.getResultList();
        if (tr.size() > 0) {
            return tr.get(0);
        } else {
            return null;
        }

    }
}
