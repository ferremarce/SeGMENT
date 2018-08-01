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
import segment.modelo.Tramitacion;

/**
 *
 * @author jmferreira
 */
@Stateless
public class TramitacionFacade extends AbstractFacade<Tramitacion> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TramitacionFacade() {
        super(Tramitacion.class);
    }

    public List<Tramitacion> findAllTramitacion(Integer idEstado, Integer idDependencia) {
        Query q = em.createQuery("SELECT a FROM Tramitacion a WHERE a.idEstadoTramite.idSubTipo=:xIdEstado AND a.idDependencia.idDependencia=:xIdDependencia ORDER BY a.fechaTramite DESC");
        q.setParameter("xIdEstado", idEstado);
        q.setParameter("xIdDependencia", idDependencia);
        //q.setParameter("xIdDependencia", idDependencia);
        List<Tramitacion> tr = q.getResultList();
        return tr;
    }

    public List<Tramitacion> findAllTramitacionProcesados(Integer idDependencia) {
        //Todos los estados menos el Pendiente
        Query q = em.createQuery("SELECT a FROM Tramitacion a WHERE a.idEstadoTramite.idSubTipo NOT IN (6) AND a.idDependencia.idDependencia=:xIdDependencia ORDER BY a.fechaTramite DESC");
        q.setParameter("xIdDependencia", idDependencia);
        //q.setParameter("xIdDependencia", idDependencia);
        List<Tramitacion> tr = q.getResultList();
        return tr;
    }

    public List<Tramitacion> findAllTramitacionDerivadas(Integer idTramitacion) {
        Query q = em.createQuery("SELECT a FROM Tramitacion a WHERE a.idTramitacionAnterior.idTramitacion=:xIdTram ORDER BY a.idTramitacion");
        q.setParameter("xIdTram", idTramitacion);
        List<Tramitacion> tr = q.getResultList();
        return tr;
    }

}
