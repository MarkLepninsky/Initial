/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.entities.Partida;

/**
 *
 * @author IVSTOYKO
 */
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
    
}
