package PokemonTerminal.Test;

import PokemonTerminal.Pokemon.*;
import PokemonTerminal.Pokemon.Pokedex.Charmander;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Movimientos.Movimiento;

public class PokemonTest {
    public static void main(String[] args) {

        // Creamos Charmander nivel 5
        Charmander c = new Charmander(5, 0);

        System.out.println("Nombre: " + c.getNombre() + " | Nivel: " + c.getnivel());

        // Le damos XP suficiente para llegar a nivel 16 y evolucionar
        Pokemon evolucionado = c.ganarXP(20000);

        System.out.println("Después de ganar XP:");
        System.out.println("Nombre: " + evolucionado.getNombre() + " | Nivel: " + evolucionado.getnivel());

        // Comprobamos movimientos aprendidos
        System.out.println("Movimientos:");
        for (Movimiento mov : evolucionado.getMovimientos()) {
            System.out.println(mov.getNombre());
        }
        System.out.println(evolucionado);
    }
}