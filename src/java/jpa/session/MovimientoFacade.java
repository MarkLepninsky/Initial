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
import jpa.entities.Movimiento;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class MovimientoFacade extends AbstractFacade<Movimiento> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }

    public void createMovimiento(int idMovimiento, String nombreMovimiento, String descripcion) {
        em.createNativeQuery("INSERT INTO movimiento (idMovimiento, Nombre_Movimiento, Descripcion) VALUES (?, ?, ?)")
          .setParameter(1, idMovimiento)
          .setParameter(2, nombreMovimiento)
          .setParameter(3, descripcion)
          .executeUpdate();
    }

    public Movimiento findById(int idMovimiento) {
        List<Movimiento> list = em.createNativeQuery("SELECT * FROM movimiento WHERE idMovimiento = ?", Movimiento.class)
                                   .setParameter(1, idMovimiento)
                                   .getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updateDescripcion(int idMovimiento, String nuevaDescripcion) {
        em.createNativeQuery("UPDATE movimiento SET Descripcion = ? WHERE idMovimiento = ?")
          .setParameter(1, nuevaDescripcion)
          .setParameter(2, idMovimiento)
          .executeUpdate();
    }

    public void deleteMovimiento(int idMovimiento) {
        em.createNativeQuery("DELETE FROM movimiento WHERE idMovimiento = ?")
          .setParameter(1, idMovimiento)
          .executeUpdate();
    }

    public List<Movimiento> findAllMovimientos() {
        return em.createNativeQuery("SELECT * FROM movimiento", Movimiento.class)
                 .getResultList();
    }

    public int getMaxId() {
        List<Integer> list = em.createNativeQuery("SELECT MAX(idMovimiento) FROM movimiento")
                               .getResultList();
        if (list.isEmpty() || list.get(0) == null) {
            return 0;
        } else {
            return list.get(0);
        }
    }

    public void linkMovimientoToPartida(int idPartida, int idMovimiento) {
        em.createNativeQuery("INSERT INTO partida_has_movimiento (Partida_idPartida, Movimiento_idMovimiento) VALUES (?, ?)")
          .setParameter(1, idPartida)
          .setParameter(2, idMovimiento)
          .executeUpdate();
    }

    public List<Integer> findMovimientosByPartida(int idPartida) {
        return em.createNativeQuery("SELECT Movimiento_idMovimiento FROM partida_has_movimiento WHERE Partida_idPartida = ?")
                 .setParameter(1, idPartida)
                 .getResultList();
    }
}
