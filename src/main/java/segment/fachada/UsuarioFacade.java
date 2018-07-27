/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package segment.fachada;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import segment.modelo.Usuario;

/**
 *
 * @author jmferreira
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "segment.org_SEGMENT_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario findUsuarioByCuenta(String cuenta) {
        Query q = em.createQuery("SELECT a FROM Usuario a WHERE a.cuenta=:xCuenta");
        q.setParameter("xCuenta", cuenta);
        try {
            Usuario tr = (Usuario) q.getSingleResult();
            return tr;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al recuperar el usuario de la cuenta [" + cuenta + "]", e);
            return null;
        }
    }

    public List<Usuario> findUsuario(String criterio) {
        Query q = em.createQuery("SELECT a FROM Usuario a WHERE UPPER(CONCAT(a.apellidos, a.nombres, a.cuenta)) LIKE :xCriterio");
        q.setParameter("xCriterio", "%" + criterio.toUpperCase() + "%");
        List<Usuario> tr = q.getResultList();
        return tr;

    }

}
