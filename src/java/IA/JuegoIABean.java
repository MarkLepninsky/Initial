/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author fjdomher
 */
@Named(value = "juegoIABean")
@RequestScoped
public class JuegoIABean {
    private String jugadaJugador, jugadaIA, resultado;
    public JuegoIABean() {
    }
    
    public void jugar(){
        LogicaJuego logica = new LogicaJuego();
        jugadaIA = logica.getJugadaIA();
        resultado = logica.ganador(jugadaJugador, jugadaIA);
    }

    public String getJugadaJugador() {
        return jugadaJugador;
    }

    public String getJugadaIA() {
        return jugadaIA;
    }

    public String getResultado() {
        return resultado;
    }

    public void setJugadaJugador(String jugadaJugador) {
        this.jugadaJugador = jugadaJugador;
    }
    
}
