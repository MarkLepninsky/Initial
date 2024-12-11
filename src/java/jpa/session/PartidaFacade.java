package jpa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Partida;

@Stateless
public class PartidaFacade extends AbstractFacade<Partida> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PartidaFacade() {
        super(Partida.class);
    }

    public void createPartida(int idPartida, int idU1, int idU2, Integer ganadoU1, Integer ganadoU2, int nRondas, Integer nRondasHechas, Integer nRondasGU1, Integer nRondasGU2, int usuarioId1, int usuarioId2) {
        em.createNativeQuery("INSERT INTO partida (idPartida, ID_U1, ID_U2, Ganado_U1, Ganado_U2, N_Rondas, N_Rondas_Hechas, N_Rondas_G_U1, N_Rondas_G_U2, Usuario_idUsuario1, Usuario_idUsuario2) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
          .setParameter(1, idPartida)
          .setParameter(2, idU1)
          .setParameter(3, idU2)
          .setParameter(4, ganadoU1)
          .setParameter(5, ganadoU2)
          .setParameter(6, nRondas)
          .setParameter(7, nRondasHechas)
          .setParameter(8, nRondasGU1)
          .setParameter(9, nRondasGU2)
          .setParameter(10, usuarioId1)
          .setParameter(11, usuarioId2)
          .executeUpdate();
    }

    public Partida findById(int idPartida) {
        List<Partida> list = em.createNativeQuery("SELECT * FROM partida WHERE idPartida = ?", Partida.class)
                               .setParameter(1, idPartida)
                               .getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updateRondasHechas(int idPartida, int nuevasRondasHechas) {
        em.createNativeQuery("UPDATE partida SET N_Rondas_Hechas = ? WHERE idPartida = ?")
          .setParameter(1, nuevasRondasHechas)
          .setParameter(2, idPartida)
          .executeUpdate();
    }

    public void deletePartida(int idPartida) {
        em.createNativeQuery("DELETE FROM partida WHERE idPartida = ?")
          .setParameter(1, idPartida)
          .executeUpdate();
    }

    public List<Partida> findAllPartidas() {
        return em.createNativeQuery("SELECT * FROM partida", Partida.class)
                 .getResultList();
    }

    public int getMaxId() {
        List<Integer> list = em.createNativeQuery("SELECT MAX(idPartida) FROM partida")
                               .getResultList();
        if (list.isEmpty() || list.get(0) == null) {
            return 0;
        } else {
            return list.get(0);
        }
    }
}
