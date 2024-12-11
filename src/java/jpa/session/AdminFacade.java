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
import jpa.entities.Admin;

/**
 *
 * @author IVSTOYKO
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {

    @PersistenceContext(unitName = "PapelPiedraTijerasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }

    // Create an admin linked to an existing Usuario
    public void createAdmin(int usuarioId) {
        em.createNativeQuery("INSERT INTO admin (Usuario_idUsuario) VALUES (?)")
          .setParameter(1, usuarioId)
          .executeUpdate();
    }

    // Check if a user is an admin
    public boolean isAdmin(int usuarioId) {
        List<Integer> list = em.createNativeQuery("SELECT Usuario_idUsuario FROM admin WHERE Usuario_idUsuario = ?")
                               .setParameter(1, usuarioId)
                               .getResultList();
        return !list.isEmpty();
    }

    // Remove an admin by user ID
    public void removeAdmin(int usuarioId) {
        em.createNativeQuery("DELETE FROM admin WHERE Usuario_idUsuario = ?")
          .setParameter(1, usuarioId)
          .executeUpdate();
    }

    // Retrieve all admins
    public List<Admin> findAllAdmins() {
        return em.createNativeQuery("SELECT * FROM admin", Admin.class).getResultList();
    }
}
