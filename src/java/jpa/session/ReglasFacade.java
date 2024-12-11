/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Reglas;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class ReglasFacade extends AbstractFacade<Reglas> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReglasFacade() {
        super(Reglas.class);
    }

    public void createRegla(int idReglas, int idMov1, int idMov2, String resultado, int movimientoId1, int movimientoId2, String descripcion) {
        em.createNativeQuery("INSERT INTO reglas (idReglas, ID_MOV1, ID_MOV2, Resultado, Movimiento_idMovimiento, Movimiento_idMovimiento1, Descripcion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)")
          .setParameter(1, idReglas)
          .setParameter(2, idMov1)
          .setParameter(3, idMov2)
          .setParameter(4, resultado)
          .setParameter(5, movimientoId1)
          .setParameter(6, movimientoId2)
          .setParameter(7, descripcion)
          .executeUpdate();
    }

    public Reglas findById(int idReglas) {
        List<Reglas> list = em.createNativeQuery("SELECT * FROM reglas WHERE idReglas = ?", Reglas.class)
                               .setParameter(1, idReglas)
                               .getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updateDescripcion(int idReglas, String nuevaDescripcion) {
        em.createNativeQuery("UPDATE reglas SET Descripcion = ? WHERE idReglas = ?")
          .setParameter(1, nuevaDescripcion)
          .setParameter(2, idReglas)
          .executeUpdate();
    }

    public void deleteRegla(int idReglas) {
        em.createNativeQuery("DELETE FROM reglas WHERE idReglas = ?")
          .setParameter(1, idReglas)
          .executeUpdate();
    }

    public List<Reglas> findAllReglas() {
        return em.createNativeQuery("SELECT * FROM reglas", Reglas.class)
                 .getResultList();
    }

    public int getMaxId() {
        List<Integer> list = em.createNativeQuery("SELECT MAX(idReglas) FROM reglas")
                               .getResultList();
        if (list.isEmpty() || list.get(0) == null) {
            return 0;
        } else {
            return list.get(0);
        }
    }
}
