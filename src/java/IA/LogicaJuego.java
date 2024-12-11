/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;
import java.util.Random;

/**
 *
 * @author fjdomher
 */
public class LogicaJuego {
    private static final String[] JUGADAS = {"Piedra", "Papel", "Tijera"};
    
    public String getJugadaIA(){
        Random random = new Random();
        return JUGADAS[random.nextInt(JUGADAS.length)];
    }
    
    public String ganador(String jugadaJugador, String jugadaIA){
        if (jugadaJugador.equals(jugadaIA)) {
            return "Empate";
        }
        switch (jugadaJugador) {
            case "Piedra":
                return jugadaIA.equals("Tijera") ? "Jugador gana" : "IA gana";
            case "Papel":
                return jugadaIA.equals("Piedra") ? "Jugador gana" : "IA gana";
            case "Tijera":
                return jugadaIA.equals("Papel") ? "Jugador gana" : "IA gana";
            default:
                return "Elección inválida";
        }
    }
}
