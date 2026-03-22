package PokemonTerminal.Test;

import PokemonTerminal.Estados.EstadoAlterado;
import PokemonTerminal.Estados.Paralisis;
import PokemonTerminal.Items.Evolutivos.PiedraFuego;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Pokemon.*;
import PokemonTerminal.Pokemon.Pokedex.*;
import PokemonTerminal.Pokemon.Pokedex.Wartortle;
import PokemonTerminal.Pokemon.Pokemon;
import PokemonTerminal.Movimientos.Movimiento;
import PokemonTerminal.Tipos.Estado;

public class PokemonTest {
    public static void main(String[] args) {
        PiedraFuego piedraFuego = new PiedraFuego();
        Paralisis paralisis = new Paralisis();
        Lanzallamas lanzallamas = new Lanzallamas();

        // Creamos Charmander nivel 5
        Charmander c = new Charmander(5, 0);
        Blastoise b = new Blastoise(5, 0);

        // Le damos XP suficiente para llegar a nivel 35 y evolucionar
        Pokemon charmander = c.ganarXP(400000);
        Pokemon blastoise = b.ganarXP(600000);
        lanzallamas.ejecutar(charmander,blastoise);

        System.out.println(charmander.toString());
        System.out.println(blastoise.toString());
    }
}