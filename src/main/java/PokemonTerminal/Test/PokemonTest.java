package PokemonTerminal.Test;

import PokemonTerminal.Estados.EstadoAlterado;
import PokemonTerminal.Estados.Paralisis;
import PokemonTerminal.Items.Evolutivos.PiedraFuego;
import PokemonTerminal.Items.MT.MT;
import PokemonTerminal.Movimientos.Especiales.Lanzallamas;
import PokemonTerminal.Movimientos.Especiales.Rayo;
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
        Rayo rayo = new Rayo();
        MT mtRayo = new MT(2000, "MT24 - Rayo", new Rayo());
        // Creamos Charmander nivel 5
        Charmander c = new Charmander(5, 0);
        Squirtle s = new Squirtle(5, 0);
        // Le damos XP suficiente para llegar a nivel 35 y evolucionar
        Pokemon charmander = c.ganarXP(400000);
        Pokemon blastoise = s.ganarXP(600000);
        mtRayo.usar(charmander,2);
        //Charizard le hace un rayo a blastoise
        rayo.ejecutar(charmander,blastoise);
        System.out.println(charmander.toString());
        System.out.println(blastoise.toString());
    }
}