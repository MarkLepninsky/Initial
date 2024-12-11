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
import jpa.entities.Player;
import jpa.entities.Usuario;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class PlayerFacade extends AbstractFacade<Player> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlayerFacade() {
        super(Player.class);
    }

    public void createPlayer(int idUsuario) {
        em.createNativeQuery("INSERT INTO player (Usuario_idUsuario) VALUES (?)")
          .setParameter(1, idUsuario)
          .executeUpdate();
    }

    public Usuario findUsuarioByPlayer(int idUsuario) {
        List<Usuario> list = em.createNativeQuery("SELECT * FROM usuario WHERE idUsuario = ?", Usuario.class)
                               .setParameter(1, idUsuario)
                               .getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public void updatePlayerStats(int idUsuario, int victorias, int derrotas, int empates) {
        em.createNativeQuery("UPDATE usuario SET N_Victorias = ?, N_Derrotas = ?, N_Empates = ? WHERE idUsuario = ?")
          .setParameter(1, victorias)
          .setParameter(2, derrotas)
          .setParameter(3, empates)
          .setParameter(4, idUsuario)
          .executeUpdate();
    }

    public void deletePlayer(int idUsuario) {
        em.createNativeQuery("DELETE FROM player WHERE Usuario_idUsuario = ?")
          .setParameter(1, idUsuario)
          .executeUpdate();
    }

    public List<Usuario> findAllPlayers() {
        return em.createNativeQuery("SELECT u.* FROM usuario u INNER JOIN player p ON u.idUsuario = p.Usuario_idUsuario", Usuario.class)
                 .getResultList();
    }
}
