/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.fachada;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import segment.modelo.SubTipo;

/**
 *
 * @author jmferreira
 */
@Stateless
public class SubTipoFacade extends AbstractFacade<SubTipo> {

    private static final Logger LOG = Logger.getLogger(SubTipoFacade.class.getName());

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubTipoFacade() {
        super(SubTipo.class);
    }

    public List<SubTipo> findAllbyTipo(Integer idTipo, Boolean soloPadre) {
        String sql;
        if (soloPadre) {
            sql = "SELECT a FROM SubTipo a WHERE a.idTipo.idTipo=:xIdTipo AND a.idPadre IS NULL ORDER BY a.descripcionSubTipo";
        } else {
            sql = "SELECT a FROM SubTipo a WHERE a.idTipo.idTipo=:xIdTipo ORDER BY a.descripcionSubTipo";
        }
        Query q = em.createQuery(sql);
        q.setParameter("xIdTipo", idTipo);
        LOG.log(Level.INFO, "findAllbyTipo: {0}", sql);
        List<SubTipo> tr = q.getResultList();
        return tr;
    }

}
